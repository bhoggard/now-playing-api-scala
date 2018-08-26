package parsers

import models.Piece
import org.scalatestplus.play.PlaySpec
import play.api.libs.json.Json
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ParsersSpec extends PlaySpec {
  "CounterstreamParser" should {
    "parse counterstream data" in {
      CounterstreamParser.parse(Future("JB Floyd - Variations")).map { s =>
        s mustEqual Some(
          Piece(title = "Variations", composer = "JB Floyd")
        )
      }
    }
  }

  "Q2Parser" should {
    "parse q2 data" in {
      val json =
        Json.parse(scala.io.Source.fromFile("test/resources/q2.json").mkString)
      Q2Parser.parse(Future(json)).map { s =>
        s mustEqual Some(
          Piece(title = "Mask", composer = "Visible Cloaks")
        )
      }
    }
  }

  "SomaFMParser" should {
    "parse dronezone data" in {
      val xml =
        scala.io.Source.fromFile("test/resources/dronezone.xml").toString
      SomaFMParser.parse(Future(xml)).map { s =>
        s mustEqual Some(
          Piece(title = "the illusion of gradualness",
                composer = "peter savage")
        )
      }
    }
  }

  "YleParser" should {
    "parse yle data" in {
      val xml = scala.io.Source.fromFile("test/resources/yle.xml").toString
      YleParser.parse(Future(xml)).map { s =>
        s mustEqual Some(
          Piece(title = "Sonaatti viululle ja pianolle A-duuri",
                composer = "Franck, César [1822-1890]")
        )
      }
    }
  }

}
