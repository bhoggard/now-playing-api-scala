package services

import models.Piece
import org.scalatestplus.play.PlaySpec
import play.api.libs.json.{JsObject, Json}

class FeedParserSpec extends PlaySpec {

  "FeedParser" should {
    "parse q2 data" in {
      val json = Json.parse(scala.io.Source.fromFile("test/resources/q2.json").mkString)
      val pieceOpt = FeedParser.translate_q2(json)
      pieceOpt mustEqual Some(Piece(title = "Mask", composer =  "Visible Cloaks"))
    }
  }

}
