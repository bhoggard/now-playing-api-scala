package services

import com.softwaremill.sttp._
import com.softwaremill.sttp.asynchttpclient.future.AsyncHttpClientFutureBackend
import models.Piece
import parsers._
import play.api.libs.json._

import scala.concurrent.{ ExecutionContext, Future }

class FeedService(implicit ec: ExecutionContext) {
  implicit val sttpBackend: SttpBackend[Future, Nothing] =
    AsyncHttpClientFutureBackend()

  def counterstream: Future[Option[Piece]] = {
    val body = sendRequest(
      uri"http://counterstream.newmusicusa.org:8000/currentsong?sid=1"
    )
    CounterstreamParser.parse(body)
  }

  def dronezone: Future[Option[Piece]] = {
    val response = sendRequest(
      uri"http://api.somafm.com/recent/dronezone.tre.xml"
    )
    SomaFMParser.parse(response)
  }

  def q2: Future[Option[Piece]] = {
    val response = loadJson(uri"https://api.wnyc.org/api/v1/whats_on/q2/")

    Q2Parser.parse(response)
  }

  def yle: Future[Option[Piece]] = {
    val body = sendRequest(
      uri"https://yle.fi/radiomanint/LiveXML/r17/item(0).xml"
    )
    YleParser.parse(body)
  }

  private def loadJson(uri: Uri): Future[JsValue] = {
    sendRequest(uri) map { r =>
      Json.parse(r)
    }
  }

  private def sendRequest(
    uri: Uri
  ): Future[String] = {
    val request  = sttp.get(uri)
    val response = request.send()
    response map { r =>
      r.unsafeBody
    }
  }
}
