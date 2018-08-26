package parsers

import models.Piece

import scala.concurrent.{ ExecutionContext, Future }

object CounterstreamParser {

  def parse(
    fs: Future[String]
  )(implicit ec: ExecutionContext): Future[Option[Piece]] = fs map { s =>
    val composerAndTitle = s.split(" - ")
    val piece =
      Piece(title = composerAndTitle(1), composer = composerAndTitle(0))
    Some(piece)
  }
}
