package controllers

import javax.inject._
import models.Piece
import play.api.libs.json.Json
import play.api.mvc._
import services.FeedService

import scala.concurrent.ExecutionContext.Implicits.global
@Singleton
class ApiController @Inject()(cc: ControllerComponents)
    extends AbstractController(cc) {

  val feedService = new FeedService()

  implicit val pieceWriter = Json.writes[Piece]

  def counterstream() = Action.async { implicit request: Request[AnyContent] =>
    feedService.counterstream.map { json =>
      Ok(Json.toJson(json))
    }
  }

  def dronezone() = Action.async { implicit request: Request[AnyContent] =>
    feedService.dronezone.map { json =>
      Ok(Json.toJson(json))
    }
  }

  def q2() = Action.async { implicit request: Request[AnyContent] =>
    feedService.q2.map { json =>
      Ok(Json.toJson(json))
    }
  }

  def yle() = Action.async { implicit request: Request[AnyContent] =>
    feedService.yle.map { json =>
      Ok(Json.toJson(json))
    }
  }

}
