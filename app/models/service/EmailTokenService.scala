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
 * terms, you may contact by email Sergey Kotlov, sergey.kotlov@happymelly.com
 * or in writing
 * Happy Melly One, Handelsplein 37, Rotterdam, The Netherlands, 3071 PR
 */
package models.service

import models.EmailToken
import models.database.EmailTokens
import models.database.PortableJodaSupport._
import org.joda.time.DateTime
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB

/**
  * Contains methods for managing EmailToken records in database
  */
class EmailTokenService extends Services {

  private val tokens = TableQuery[EmailTokens]

  /**
    * Deletes a token for the given token identifier
    * @param token Token identifier
    */
  def delete(token: String): Unit = DB.withSession { implicit session =>
    tokens.filter(_.token === token).delete
  }

  /**
    * Deletes all expired tokens
    */
  def deleteExpiredTokens(): Unit = DB.withSession { implicit session =>
    tokens.filter(_.expire < DateTime.now()).mutate(_.delete())
  }

  /**
    * Returns an email token if it exists
    * @param token Token identifier
    */
  def find(token: String): Option[EmailToken] = DB.withSession { implicit session =>
    tokens.filter(_.token === token).firstOption
  }

  /**
    * Adds new token to the database
    * @param token Token
    */
  def insert(token: EmailToken): EmailToken = DB.withSession { implicit session =>
    tokens.insert(token)
    token
  }
}

object EmailTokenService {
  private val _instance = new EmailTokenService

  def get: EmailTokenService = _instance
}