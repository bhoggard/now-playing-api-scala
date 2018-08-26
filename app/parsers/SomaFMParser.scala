package parsers

import models.Piece

import scala.concurrent.{ ExecutionContext, Future }
import scala.xml.XML

object SomaFMParser {
  def parse(
    fs: Future[String]
  )(implicit ec: ExecutionContext): Future[Option[Piece]] = fs map { s =>
    val xml    = XML.loadString(s)
    val title  = xml \\ "event" \\ "title" text
    val artist = xml \\ "event" \\ "artist" text

    Some(Piece(title = title, composer = artist))
  }
}
