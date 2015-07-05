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
 * If you have questions concerning this license or the applicable additional terms, you may contact
 * by email Sergey Kotlov, sergey.kotlov@happymelly.com or
 * in writing Happy Melly One, Handelsplein 37, Rotterdam, The Netherlands, 3071 PR
 */

package models

import models.database.FacilitatorLanguages
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import play.api.Play.current

/**
 * This class represents a language which a facilitator speaks
 * @param personId Facilitator identifier
 * @param language Two-letter language identifier
 */
case class FacilitatorLanguage(personId: Long, language: String) {

  /**
   * Insert a new language to DB
   * @return
   */
  def insert: FacilitatorLanguage = DB.withSession { implicit session ⇒
    TableQuery[FacilitatorLanguages] += this
    this
  }

  /**
   * Delete a language-facilitator connection
   */
  def delete(): Unit = DB.withSession { implicit session ⇒
    TableQuery[FacilitatorLanguages].
      filter(_.personId === personId).
      filter(_.language === language).delete
  }
}
