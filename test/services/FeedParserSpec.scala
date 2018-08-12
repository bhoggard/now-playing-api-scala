package services

import models.Piece
import org.scalatestplus.play.PlaySpec
import play.api.libs.json.Json

class FeedParserSpec extends PlaySpec {

  "Q2Parser" should {
    "parse q2 data" in {
      val json = Json.parse(scala.io.Source.fromFile("test/resources/q2.json").mkString)
      val pieceOpt = Q2Parser.parse(json)
      pieceOpt mustEqual Some(Piece(title = "Mask", composer =  "Visible Cloaks"))
    }
  }

}
