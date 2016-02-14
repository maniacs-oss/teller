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
package models.repository.admin

import models.admin.ApiToken
import models.database.admin.ApiTokenTable
import play.api.Application
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ApiTokenRepository(app: Application) extends HasDatabaseConfig[JdbcProfile]
  with ApiTokenTable {

  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](app)
  import driver.api._
  private val tokens = TableQuery[ApiTokens]

  /**
   * Returns ApiToken if exists, None otherwise
   * @param id Token identifier
   */
  def find(id: Long): Future[Option[ApiToken]] = db.run(tokens.filter(_.id === id).result).map(_.headOption)

  /**
   * Returns ApiToken if exists, None otherwise
   * @param token Token
   */
  def find(token: String): Future[Option[ApiToken]] =
    db.run(tokens.filter(_.token === token).result).map(_.headOption)
}