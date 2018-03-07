import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

import scala.io.StdIn

object Runner extends App {

  implicit val actorSystem = ActorSystem("gameOfLife")
  implicit val materializer = ActorMaterializer()
//  implicit val executionContext = actorSystem.dispatcher

  val gameActor = actorSystem.actorOf(Props[GameActor], "GameActor")

  gameActor ! StartGame(20,20,200)



//  val httpRoutes = new HttpRoutes(playerA, playerB)
//
//  val bindingFuture = Http().bindAndHandle(httpRoutes.route, "localhost", 8080)
//
//  StdIn.readLine()
//
//  bindingFuture
//    .flatMap(_.unbind())
//    .onComplete(_ => actorSystem.terminate())

}
