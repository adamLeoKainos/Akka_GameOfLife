import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActor, TestKit, TestProbe}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class GameActorTest extends TestKit(ActorSystem("test")) with ImplicitSender with WordSpecLike with Matchers with BeforeAndAfterAll {

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "Game actor" must {

    "start the game by sending specs to GameActor" in {
//      val gameActor = TestProbe()
      val gameActor = system.actorOf(Props[GameActor])
//      val cell =

    }

  }

}