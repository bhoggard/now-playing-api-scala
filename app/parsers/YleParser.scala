package parsers

import models.Piece

import scala.xml.Elem

object YleParser {
  def parse(xml: Elem): Option[Piece] = {
    val title = xml \\ "RMPADEXPORT" \\ "ITEM" \@ "TITLE"

    val role = (xml \\ "RMPADEXPORT" \\ "ROLES" \\ "ROLE")
      .find(r => (r \\ "ROLE_NAME" text) == "COMPOSER")

    val composer = role.get \\ "PERSON_NAME" text

    Some(Piece(title = title, composer = composer))
  }
}
