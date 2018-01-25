
import controller.ChessController
import view.swing.Gui
import view.tui

import scala.io.StdIn.readLine

object Schach {
    val controller = new ChessController
    val tui = new tui(controller)
    val gui = Gui(controller)


  def main(args: Array[String]) {
        var input: String = ""

        do {
          input = readLine()
          tui.processInputLine(input)

        } while (input != "q")

  }
}

