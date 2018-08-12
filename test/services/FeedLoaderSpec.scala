package services

import models.Piece
import org.scalatestplus.play.PlaySpec
import play.api.libs.json.{JsObject, Json}

class FeedLoaderSpec extends PlaySpec {

  "JSON translators" should {
    "process q2 data" in {
      val json = Json.parse(scala.io.Source.fromFile("test/resources/q2.json").mkString)
      val piece = FeedLoader.translate_q2(json)
      piece mustEqual Piece(title = "Mask", composer =  "Visible Cloaks")
    }
  }

}
