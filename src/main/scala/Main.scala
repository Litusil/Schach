
import controller.ChessController
import view.tui
import scala.io.StdIn.readLine

object Main {
    val controller = new ChessController()
    val tui = new tui(controller)
    controller.notifyObservers()


  def main(args: Array[String]) {
        var input: String = ""

        do {
          input = readLine()
          tui.processInputLine(input)
        } while (input != "q")

  }
}

