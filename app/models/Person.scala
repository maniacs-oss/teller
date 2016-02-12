
/*
 * Happy Melly Teller
 * Copyright (C) 2013 - 2016, Happy Melly http://www.happymelly.com
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
 * If you have questions concerning this license or the applicable additional terms, you may contact
 * by email Sergey Kotlov, sergey.kotlov@happymelly.com or
 * in writing Happy Melly One, Handelsplein 37, Rotterdam, The Netherlands, 3071 PR
 */

package models

import models.service._
import org.joda.money.Money
import org.joda.time.{DateTime, LocalDate}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

/**
 * A person, such as the owner or employee of an organisation.
 */
case class Person(
  id: Option[Long],
  firstName: String,
  lastName: String,
  override val email: String,
  birthday: Option[LocalDate],
  photo: Photo,
  signature: Boolean,
  addressId: Long,
  bio: Option[String],
  interests: Option[String],
  webSite: Option[String],
  blog: Option[String],
  customerId: Option[String] = None,
  virtual: Boolean = false,
  active: Boolean = true,
  dateStamp: DateStamp) extends Recipient with ActivityRecorder {

  private var _socialProfile: Option[SocialProfile] = None
  private var _address: Option[Address] = None
  private var _organisations: Option[List[Organisation]] = None

  def copy(id: Option[Long] = id,
    firstName: String = firstName,
    lastName: String = lastName,
    email: String = email,
    birthday: Option[LocalDate] = birthday,
    photo: Photo = photo,
    signature: Boolean = signature,
    addressId: Long = addressId,
    bio: Option[String] = bio,
    interests: Option[String] = interests,
    webSite: Option[String] = webSite,
    blog: Option[String] = blog,
    customerId: Option[String] = customerId,
    virtual: Boolean = virtual,
    active: Boolean = active,
    dateStamp: DateStamp = dateStamp): Person = {

    val person = Person(id, firstName, lastName, email, birthday, photo,
      signature, addressId, bio, interests, webSite, blog,
      customerId, virtual, active, dateStamp)

    this._socialProfile foreach { p ⇒
      person.socialProfile_=(this.socialProfile)
    }

    this._address foreach { a ⇒
      person.address_=(a)
    }
    person
  }

  def socialProfile: SocialProfile = _socialProfile.get

  def socialProfile_=(socialProfile: SocialProfile): Unit = {
    _socialProfile = Some(socialProfile)
  }

  def address: Address = _address.get

  def address_=(address: Address): Unit = {
    _address = Some(address)
  }

  /**
   * Returns a list of organisations this person is a member of
   */
  def organisations(implicit services: Services): List[Organisation] = if (_organisations.isEmpty) {
    val orgs = Await.result(services.personService.memberships(identifier), 3.seconds)
    organisations_=(orgs)
    _organisations.get
  } else {
    _organisations.get
  }

  def organisations_=(organisations: List[Organisation]): Unit = {
    _organisations = Some(organisations)
  }

  def fullName: String = firstName + " " + lastName

  def uniqueName: String = fullName.toLowerCase.replace(".", "_").replace(" ", ".")

  def name = fullName

  def fullNamePossessive = if (lastName.endsWith("s")) s"$fullName’" else s"$fullName’s"

  /**
   * Associates this person with given organisation.
   */
  def addRelation(organisationId: Long, services: Services): Unit =
    services.personService.addRelation(this.id.get, organisationId)

  /**
   * Returns true if it is possible to grant log in access to this user.
   */
  def canHaveUserAccount: Boolean = socialProfile.defined

  /**
   * Deletes a relationship between this person and the given organisation
   *
   * @param organisationId Organisation identifier
   */
  def deleteRelation(organisationId: Long, services: Services): Unit =
    services.personService.deleteRelation(this.id.get, organisationId)

  /**
   * Returns identifier of the object
   */
  def identifier: Long = id.getOrElse(0)

  /**
   * Returns string identifier which can be understood by human
   *
   * For example, for object 'Person' human identifier is "[FirstName] [LastName]"
   */
  def humanIdentifier: String = fullName

  /**
   * Returns type of this object
   */
  def objectType: String = Activity.Type.Person


  def summary: PersonSummary = PersonSummary(id.get, firstName, lastName, active, address.countryCode)

  /**
   * Adds member record to database
   * @param funder Defines if this person becomes a funder
   * @param fee Amount of membership fee this person paid
   * @return Returns member object
   */
  def becomeMember(funder: Boolean, fee: Money, services: Services): Future[Member] = {
    val m = services.memberService.insert(membership(funder, fee))
    services.profileStrengthService.find(id.get, false).filter(_.isDefined) foreach { x ⇒
      services.profileStrengthService.update(ProfileStrength.forMember(x.get))
    }
    m
  }

  /**
   * Returns a one-year membership object for the given parameters
   *
   * @param funder If true member is a funder
   * @param fee An amount of membership fee
   */
  protected def membership(funder: Boolean, fee: Money): Member =
    new Member(None, id.get, person = true,
      funder = funder, fee = fee,
      renewal = true,
      since = LocalDate.now(),
      until = LocalDate.now().plusYears(1),
      existingObject = true, reason = None,
      created = DateTime.now(), id.get,
      DateTime.now(), id.get)
}

case class PersonSummary(id: Long, firstName: String, lastName: String, active: Boolean, countryCode: String)

object Person {

  def apply(firstName: String, lastName: String, email: String): Person = {
    val creator = firstName + " " + lastName
    Person(None, firstName, lastName, email, None, Photo.empty, signature = false,
      addressId = 0, bio = None, interests = None, webSite = None, blog = None,
      dateStamp = DateStamp(DateTime.now(), creator, DateTime.now(), creator))
  }

  def cacheId(id: Long): String = s"signatures.$id"

  def deletable(id: Long, services: Services): Future[Boolean] = for {
    c <- services.contributionService.contributions(id, isPerson = true)
    l <- services.licenseService.licensesWithBrands(id)
    o <- services.personService.memberships(id)
  } yield c.isEmpty && l.isEmpty && o.isEmpty


  def fullFileName(id: Long): String = s"signatures/$id"

  def signature(id: Long): File =
    File.image(Person.fullFileName(id), Person.cacheId(id))

  def photo(id: Long): File =
    File.image(s"photos/$id", s"photos.$id")
}
