/*
 * Happy Melly Teller
 * Copyright (C) 2013 - 2015, Happy Melly http://www.happymelly.com
 *
 * This file is part of the Happy Melly Teller.
 *
 * Happy Melly Teller is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Happy Melly Teller is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Happy Melly Teller.  If not, see <http://www.gnu.org/licenses/>.
 *
 * If you have questions concerning this license or the applicable additional
 * terms, you may contact by email Sergey Kotlov, sergey.kotlov@happymelly.com or
 * in writing Happy Melly One, Handelsplein 37, Rotterdam, The Netherlands, 3071 PR
 */

package controllers.hm

import akka.actor.ActorRef
import controllers.{AsyncController, Utilities}
import models._
import models.actors.SlackServant.InviteMember
import models.core.payment.{CreditCard, Customer, CustomerType, Payment}
import models.repository.Repositories
import org.joda.time.DateTime
import play.api.Play
import play.api.Play.current
import play.api.data.Form
import play.api.data.Forms._
import services.integrations.Integrations

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class PaymentData(token: String,
                       yearly: Boolean,
                       coupon: Option[String] = None,
                       orgId: Option[Long] = None) {}

/**
 * Defines an interface for enrollment classes
 */
trait Enrollment extends AsyncController with Integrations with MemberNotifications {

  val repos: Repositories
  val slackServant: ActorRef

  def paymentForm = Form(mapping(
    "token" -> nonEmptyText,
    "yearly" -> boolean,
    "coupon" -> optional(nonEmptyText),
    "orgId" -> optional(longNumber))(PaymentData.apply)(PaymentData.unapply))

  /**
   * Sends Slack and email notifications
    *
    * @param person Person making all membership-related actions
   * @param org Organisation which wants to become a member
   * @param member Member data
   */
  protected def notify(person: Person, org: Option[Organisation], member: Member) = org map {
      notifyAboutOrg(_, member, person)
    } getOrElse
      notifyAboutPerson(person, member)

  /**
   * Subscribes the given person to a membership list
    *
    * @param person Person
   * @param member Member data
   * @return Returns true if the person is successfully subscribed
   */
  protected def subscribe(person: Person, member: Member): Unit = {
    val membershipListId = Play.configuration.getString("mailchimp.membershipListId").getOrElse("")
    mailChimp.subscribeToMembershipList(membershipListId, person, member.funder)
    val newsletterListId = Play.configuration.getString("mailchimp.newsletterListId").getOrElse("")
    mailChimp.subscribeToNewsletterList(newsletterListId, person)

    slackServant ! InviteMember(person.identifier)
  }

  /**
    * Inserts new customer record for the given person/organisation
    * @param customerId Stripe Customer identifier
    * @param card Card
    * @param person Person
    * @param org Organisation
    */
  protected def addCustomerRecord(customerId: String,
                                  card: CreditCard,
                                  person: Person,
                                  org: Option[Organisation]): Future[CreditCard] = {
    val recordInfo = DateStamp(DateTime.now(), person.fullName, DateTime.now(), person.fullName)
    val customer = org map { x =>
      Customer(None, customerId, x.identifier, CustomerType.Organisation, recordInfo)
    } getOrElse {
      Customer(None, customerId, person.identifier, CustomerType.Person, recordInfo)
    }
    for {
      c <- repos.core.customer.insert(customer)
      cd <- repos.core.card.insert(card.copy(customerId = c.id.get))
    } yield cd
  }

  /**
   * Makes a payment through the payment gateway and creates
   * an yearly subscription
   *
   * @param person Person making all membership-related actions
   * @param org Organisation which want to become a member
   * @param data Payment data
   * @return Returns customer identifier in the payment system and credit card info
   */
  protected def payMembership(person: Person,
                              org: Option[Organisation],
                              data: PaymentData): Future[(String, CreditCard)] = {
    val key = Play.configuration.getString("stripe.secret_key").get
    val payment = new Payment(key)
    val countryCode = org match {
      case None => person.address.countryCode
      case Some(organisation) => organisation.countryCode
    }
    val planId = Payment.stripePlanId(countryCode, data.yearly)
    val (customerId, card) = payment.subscribe(person, org, data.token, planId, data.coupon)

    addCustomerRecord(customerId, card, person, org) map { card ⇒
      (customerId, card)
    }
  }

  /**
    * Returns fees paid by member
    * @param data Payment data
    * @param country Country
    */
  protected def paidFee(data: PaymentData, country: String): BigDecimal = {
    val fees = Payment.countryBasedPlans(country)
    if (data.yearly)
      BigDecimal(fees._2.toDouble)
    else
      BigDecimal(fees._1.toDouble)
  }


  private def notifyAboutPerson(person: Person, member: Member) = {
    val url: String = controllers.core.routes.People.details(person.id.get).url
    slack.send(personSlackMsg(person, member, url))
    if (!member.funder)
      sendWelcomeEmail(person, member, person.firstName)
  }

  private def notifyAboutOrg(org: Organisation, member: Member, person: Person) = {
    val url: String = controllers.core.routes.Organisations.details(org.identifier).url
    slack.send(newMemberMsg(member, org.name, url))
    if (!member.funder)
      sendWelcomeEmail(person, member, org.name)
  }

  private def sendWelcomeEmail(person: Person, member: Member, name: String) = {
    val profileUrl = Utilities.fullUrl(Members.profileUrl(member))
    val body = mail.html.welcome(profileUrl, member.profileUrl, name).toString()
    email.sendSystem(Seq(person), "Welcome to Happy Melly network", body)
  }

  private def personSlackMsg(person: Person, member: Member, url: String): String = {
    val headline = newMemberMsg(member, person.fullName, url)
    val dummy = SocialProfile()
    connectMeMessage(dummy, person.profile) map { value =>
      headline + " " + value
    } getOrElse
      headline
  }
}
