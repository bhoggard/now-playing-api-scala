package parsers

import models.Piece

import scala.concurrent.{ ExecutionContext, Future }
import scala.xml.XML

object YleParser {
  def parse(
    fs: Future[String]
  )(implicit ec: ExecutionContext): Future[Option[Piece]] = fs map { s =>
    val xml   = XML.loadString(s)
    val title = xml \\ "RMPADEXPORT" \\ "ITEM" \@ "TITLE"
    val role = (xml \\ "RMPADEXPORT" \\ "ROLES" \\ "ROLE")
      .find(r => (r \\ "ROLE_NAME" text) == "COMPOSER")
    val composer = role.get \\ "PERSON_NAME" text

    Some(Piece(title = title, composer = composer))
  }
}
