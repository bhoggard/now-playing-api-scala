package parsers

import models.Piece

import scala.xml.Elem

object SomaFMParser {
  def parse(xml: Elem): Option[Piece] = {
    val title  = (xml \\ "event" \\ "title" text)
    val artist = (xml \\ "event" \\ "artist" text)
    Some(Piece(title = title, composer = artist))
  }
}
