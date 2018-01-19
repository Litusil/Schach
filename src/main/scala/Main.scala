
import controller.ChessController
import model.fileIO.toXML
import view.swing.Gui
import view.tui

import scala.io.StdIn.readLine

object Main {
    val controller = new ChessController()
    val tui = new tui(controller)
    val gui = new Gui(controller);
    val xml = new toXML()
    xml.save(controller.chessBoard)
    controller.notifyObservers()


  def main(args: Array[String]) {
        var input: String = ""

        do {
          input = readLine()
          tui.processInputLine(input)

        } while (input != "q")

  }
}

