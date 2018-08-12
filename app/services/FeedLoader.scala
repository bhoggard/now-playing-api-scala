package services

import models.Piece
import play.api.libs.json
import play.api.libs.json._

object FeedLoader {

  def translate_q2(json: JsValue): Piece = {
    val titleRead = (JsPath \ "current_playlist_item" \ "catalog_entry" \ "title").read[String]
    val composerRead = (JsPath \ "current_playlist_item" \ "catalog_entry" \ "composer" \ "name").read[String]


    Piece(title = processReader(json, titleRead), composer = processReader(json, composerRead))
  }

  private def processReader(json: JsValue, read: Reads[String]) = {
    val result: JsResult[String] = json.validate[String](read)

    result match {
      case s: JsSuccess[String] => s.get
      case e: JsError => "Errors: " + JsError.toJson(e).toString()
    }
  }

}
