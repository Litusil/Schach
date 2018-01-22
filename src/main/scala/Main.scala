

import controller.ChessController
import model.fileIOInterface
import model.toJson
import model.toXML
import view.swing.Gui
import view.tui

import scala.io.StdIn.readLine

object Main {
    val config = scala.xml.XML.loadFile("config.xml")
    val savesystem = (config \\ "slmanager" \ "@type")
    var slmanager:fileIOInterface = new toXML
    if (savesystem.equals("JSON") ){
      slmanager = new toJson
    }

    val controller = new ChessController(slmanager)
    val tui = new tui(controller)
    val gui = new Gui(controller);


  def main(args: Array[String]) {
        var input: String = ""

        do {
          input = readLine()
          tui.processInputLine(input)

        } while (input != "q")

  }
}

