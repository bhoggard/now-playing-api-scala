package services

import com.softwaremill.sttp._
import models.Piece
import parsers._
import play.api.libs.json._

import scala.xml.XML

object FeedService {
  def counterstream: Option[Piece] = {
    val body = bodyAsString(
      uri"http://counterstream.newmusicusa.org:8000/currentsong?sid=1"
    )
    CounterstreamParser.parse(body)
  }

  def dronezone: Option[Piece] = {
    val xml = XML.load("http://api.somafm.com/recent/dronezone.tre.xml")
    SomaFMParser.parse(xml)
  }

  def q2(): Option[Piece] = {
    val response = loadJson(uri"https://api.wnyc.org/api/v1/whats_on/q2/")

    Q2Parser.parse(response)
  }

  def yle: Option[Piece] = {
    val xml = XML.load("https://yle.fi/radiomanint/LiveXML/r17/item(0).xml")
    YleParser.parse(xml)
  }

  private def loadJson(uri: Uri) = Json.parse(bodyAsString(uri))

  // private def loadXml(uri: Uri) = XML.load(bodyAsString(uri))

  private def bodyAsString(uri: Uri) = {
    val request          = sttp.get(uri)
    implicit val backend = HttpURLConnectionBackend()
    val response         = request.send()
    response.unsafeBody
  }
}
