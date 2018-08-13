package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class ApiControllerSpec
    extends PlaySpec
    with GuiceOneAppPerTest
    with Injecting {

  "ApiController GET" should {

    "render the JSON for q2" in {
      val controller = new ApiController(stubControllerComponents())
      val q2         = controller.q2().apply(FakeRequest(GET, "/"))

      status(q2) mustBe OK
      contentType(q2) mustBe Some("application/json")
    }
  }
}
