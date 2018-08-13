package parsers

import models.Piece

object CounterstreamParser {

  def parse(s: String): Option[Piece] = {
    val composerAndTitle = s.split(" - ")
    val piece =
      Piece(title = composerAndTitle(1), composer = composerAndTitle(0))
    Some(piece)
  }
}
