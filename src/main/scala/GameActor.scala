import java.util.ArrayList

import akka.actor.{Actor, ActorRef, Props}

class GameActor extends Actor{

  var cells: ArrayList[ArrayList[ActorRef]] = new ArrayList()
  var board: ArrayList[ArrayList[Boolean]] = new ArrayList()

  var nrOfCellsInBoard: Int = 0
  var nrOfMessagesReceived: Int = 0
  var iteration: Int = 0
  var iterationMax: Int = 0


  override def receive: Receive = {
    case StartGame(rows, columns, maxIterations) => {
      println("START THE GAME")
      populateBoard(rows, columns)
      this.iterationMax = maxIterations
      this.nrOfCellsInBoard = rows * columns
      var createdBoard: ArrayList[ArrayList[ActorRef]] = new ArrayList[ArrayList[ActorRef]]
      for (a <- 0 until rows) {
        var columnsInRow: ArrayList[ActorRef] = new ArrayList[ActorRef]
        for (b <- 0 until columns) {
          columnsInRow.add(context.actorOf(Props[Cell]))
        }
        createdBoard.add(columnsInRow)
      }
      this.cells = createdBoard

      for (a <- 0 until rows) {
        for (b <- 0 until columns) {
          var cellNeighbours: List[ActorRef] = List()
          var cell: ActorRef = cells.get(a).get(b)
          for (c <- a-1 to a+1) {
            for (d <- b-1 to b+1) {
              if (c>=0 && d>=0 && c<rows && d<columns && (c!=a || d!=b) ) {
                var neighbour: ActorRef = cells.get(c).get(d)
                cellNeighbours = cellNeighbours++(List(neighbour))
              }
            }
          }

          cell ! Init(cellNeighbours, a, b)

        }
      }
    }

    case ConfirmInit => {
      nrOfMessagesReceived += 1
      if (nrOfMessagesReceived == nrOfCellsInBoard) {
        cells.forEach(_.forEach(_ ! Calculate))
        nrOfMessagesReceived = 0
      }
    }

    case DoneCalculating => {
      nrOfMessagesReceived += 1
      if (nrOfMessagesReceived == nrOfCellsInBoard) {
        cells.forEach(_.forEach(_ ! ChangeState))
        nrOfMessagesReceived = 0
      }
    }

    case StateChanged(isAlive: Boolean, coordinates) => {
      nrOfMessagesReceived += 1
      var (x,y) = coordinates
      board.get(x).set(y,isAlive)
      if (nrOfMessagesReceived == nrOfCellsInBoard) {
        if (iteration < iterationMax) {
          cells.forEach(_.forEach(_ ! Calculate))
        }
        iteration +=1
        nrOfMessagesReceived = 0
        println("\n " + iteration)
        drawBoard()

      }
    }

  }

  def populateBoard(rows: Int, col: Int) = {
    var createdBoard: ArrayList[ArrayList[Boolean]] = new ArrayList[ArrayList[Boolean]]
    for (a <- 0 until rows) {
      var columnsInRow: ArrayList[Boolean] = new ArrayList[Boolean]
      for (b <- 0 until col) {
        columnsInRow.add(false)
      }
      createdBoard.add(columnsInRow)
    }
    this.board = createdBoard
  }

  def drawBoard() = {
    for (a <- 0 until board.size()) {
      for (b <- 0 until board.get(a).size()) {
        var isAlive = board.get(a).get(b)
        if (isAlive) print(Console.GREEN + "O")
        else print(Console.RED + "X")
//        print(if (isAlive) "X " else "O ")
      }
      println()
    }
  }
}
