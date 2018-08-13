package controllers

import javax.inject._
import models.Piece
import play.api.libs.json.Json
import play.api.mvc._
import services.FeedService

@Singleton
class ApiController @Inject()(cc: ControllerComponents)
    extends AbstractController(cc) {

  implicit val pieceWriter = Json.writes[Piece]

  def counterstream() = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(FeedService.counterstream))
  }

  def q2() = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(FeedService.q2))
  }

}
