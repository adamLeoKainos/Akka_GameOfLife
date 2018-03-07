import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.testkit.TestProbe
import org.scalatest.{Matchers, WordSpec}

class HttpRoutesTest extends WordSpec with Matchers with ScalatestRouteTest {
/*
  trait Test {
    val playerA = TestProbe()
    val playerB = TestProbe()

    val httpRoutes = new HttpRoutes(playerA.ref, playerB.ref)
  }

  "Http Routes" must {

    "allow player A to start" in new Test {

      Get("/api/playerA/5") ~> httpRoutes.route ~> check {
        handled shouldBe true
        status shouldEqual StatusCodes.OK
        responseAs[String] should include("5")

        playerA.expectMsg(Start(5))
        playerB.expectNoMsg()
      }
    }

    "allow player B to start" in new Test {
      Get("/api/playerB/7") ~> httpRoutes.route ~> check {
        handled shouldBe true
        status shouldEqual StatusCodes.OK
        responseAs[String] should include("7")

        playerB.expectMsg(Start(7))
        playerA.expectNoMsg()
      }
    }

  }
  */

}
