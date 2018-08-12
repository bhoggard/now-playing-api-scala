package services

import models.Piece
import play.api.Logger
import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._

trait FeedParser {

  implicit val reader: Reads[Piece]

  def validationResult(result: JsResult[Piece]): Option[Piece] = result match {
    case s: JsSuccess[Piece] => Some(s.get)
    case e: JsError => {
      Logger.error("Errors: " + JsError.toJson(e).toString)
      None
    }
  }

  def parse(json: JsValue): Option[Piece] =
    validationResult(json.validate[Piece])
}

object Q2Parser extends FeedParser {

  implicit val reader: Reads[Piece] = (
    (JsPath \ "current_playlist_item" \ "catalog_entry" \ "title")
      .read[String] and
      (JsPath \ "current_playlist_item" \ "catalog_entry" \ "composer" \ "name")
        .read[String]
  )(Piece.apply _)

}
