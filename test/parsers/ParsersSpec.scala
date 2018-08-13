package parsers

import models.Piece
import org.scalatestplus.play.PlaySpec
import play.api.libs.json.Json

import scala.xml.XML

class ParsersSpec extends PlaySpec {

  "CounterstreamParser" should {
    "parse counterstream data" in {
      val pieceOpt = CounterstreamParser.parse("JB Floyd - Variations")
      pieceOpt mustEqual Some(
        Piece(title = "Variations", composer = "JB Floyd")
      )
    }
  }

  "Q2Parser" should {
    "parse q2 data" in {
      val json =
        Json.parse(scala.io.Source.fromFile("test/resources/q2.json").mkString)
      val pieceOpt = Q2Parser.parse(json)
      pieceOpt mustEqual Some(
        Piece(title = "Mask", composer = "Visible Cloaks")
      )
    }
  }

  "SomaFMParser" should {
    "parse dronezone data" in {
      val xml      = XML.loadFile("test/resources/dronezone.xml")
      val pieceOpt = SomaFMParser.parse(xml)
      pieceOpt mustEqual Some(
        Piece(title = "the illusion of gradualness", composer = "peter savage")
      )
    }
  }

  "YleParser" should {
    "parse dronezone data" in {
      val xml      = XML.loadFile("test/resources/yle.xml")
      val pieceOpt = YleParser.parse(xml)
      pieceOpt mustEqual Some(
        Piece(title = "Sonaatti viululle ja pianolle A-duuri",
              composer = "Franck, César [1822-1890]")
      )
    }
  }

}
