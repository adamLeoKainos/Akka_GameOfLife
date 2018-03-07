import akka.actor.{Actor, ActorRef}

import scala.util.Random

class Cell extends Actor {

  var neighbours: List[ActorRef] = null
  var coordinates: (Int, Int) = (0,0)
  var isAlive: Boolean = Random.nextBoolean()
  var nrOfAliveNeigh:Int = 0
  var receivedMessages:Int = 0

  override def receive: Receive = {
    case Init(list, row, column) => {
      neighbours = list
      coordinates = (row, column)
      context.parent ! ConfirmInit
    }

    case Calculate => {
      neighbours.foreach(_ ! SendState(isAlive))
    }

    case SendState(isAlive) => {
      if(isAlive) nrOfAliveNeigh +=1
      receivedMessages +=1
      if(receivedMessages == neighbours.size) {
        context.parent ! DoneCalculating
        receivedMessages = 0
      }
    }

    case ChangeState => {
      if (nrOfAliveNeigh==3) isAlive=true
      else if (isAlive && nrOfAliveNeigh==2) isAlive=true
      else isAlive=false
      nrOfAliveNeigh=0
      context.parent ! StateChanged(isAlive, coordinates)
//      println(coordinates + " " + isAlive)
    }

  }

}
