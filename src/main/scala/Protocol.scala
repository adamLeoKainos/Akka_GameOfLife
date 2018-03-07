import akka.actor.ActorRef

case class StartGame(rows: Int, columns: Int, iterationMax: Int)

case class Init(neighbours: List[ActorRef], row: Int, column: Int)
case class ConfirmInit()

case class Calculate()
case class DoneCalculating()

case class ChangeState()
case class StateChanged(isAlive: Boolean, coordinates: (Int, Int))

case class SendState(state: Boolean)