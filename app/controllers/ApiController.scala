package controllers

import javax.inject._
import models.Piece
import play.api.libs.json.Json
import play.api.mvc._


@Singleton
class ApiController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  implicit val pieceWriter = Json.writes[Piece]


  def q2() = Action { implicit request: Request[AnyContent] =>
    val piece = Piece(title = "Eroica Symphony", composer =  "Ludwig van Beethoven")
    Ok(Json.toJson(piece))
  }
}
