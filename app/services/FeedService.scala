package services

import com.softwaremill.sttp._
import models.Piece
import parsers.{ CounterstreamParser, Q2Parser }
import play.api.libs.json._

import scala.xml.XML

object FeedService {
  def counterstream: Option[Piece] = {
    val body = bodyAsString(
      uri"http://counterstream.newmusicusa.org:8000/currentsong?sid=1"
    )
    CounterstreamParser.parse(body)
  }

  def q2(): Option[Piece] = {
    val response = loadJson(uri"https://api.wnyc.org/api/v1/whats_on/q2/")

    Q2Parser.parse(response)
  }

  private def loadJson(uri: Uri) = Json.parse(bodyAsString(uri))

  private def loadXml(uri: Uri) = XML.load(bodyAsString(uri))

  private def bodyAsString(uri: Uri) = {
    val request          = sttp.get(uri)
    implicit val backend = HttpURLConnectionBackend()
    val response         = request.send()
    response.unsafeBody
  }
}
