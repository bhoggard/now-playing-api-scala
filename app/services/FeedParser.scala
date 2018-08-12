package services

import models.Piece
import play.api.Logger
import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._

object FeedParser {

  implicit val q2Reads: Reads[Piece] = (
    (JsPath \ "current_playlist_item" \ "catalog_entry" \ "title")
      .read[String] and
      (JsPath \ "current_playlist_item" \ "catalog_entry" \ "composer" \ "name")
        .read[String]
  )(Piece.apply _)

  def translate_q2(json: JsValue): Option[Piece] = json.validate[Piece] match {
    case s: JsSuccess[Piece] => Some(s.get)
    case e: JsError => {
      Logger.error("Errors: " + JsError.toJson(e).toString)
      None
    }
  }
}
