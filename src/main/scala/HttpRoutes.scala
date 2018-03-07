import akka.actor.ActorRef
import akka.http.scaladsl.server.Directives._

class HttpRoutes(playerA: ActorRef, playerB: ActorRef) {

//  val route =
//    path("api" / "startGame" / IntNumber) { i =>
//      get {
//        playerA ! Start(i)
//        complete(s"starting from playerA, count: ${i}\n")
//      }
//    } ~ path("api" / "playerB" / IntNumber) { i =>
//      get {
//        playerB ! Start(i)
//        complete(s"starting from playerB, count: ${i}\n")
//      }
//    }

}
