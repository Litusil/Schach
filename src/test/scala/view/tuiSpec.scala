package view;

import controller.ChessController
import model._
import model.fileIOComponent.FileIOInterface
import model.fileIOComponent.fileIoXmlImpl.FileIO
import org.specs2.mutable._

class tuiSpec extends Specification{

  var slmanager:FileIOInterface = new FileIO

  "A tui" should{
    val controller = new ChessController
    val tui = new tui(controller)
    tui.processInputLine("falsche eingabe")
    val expectedString = "\n" +
      "   A|B| C| D|E| F| G|H\n" +
      "1|♖|♘|♗|♕|♔|♗|♘|♖|\n" +
      "2|♙|♙|♙|♙|♙|♙|♙|♙|\n" +
      "3|＿|＿|＿|＿|＿|＿|＿|＿|\n" +
      "4|＿|＿|＿|＿|＿|＿|＿|＿|\n" +
      "5|＿|＿|＿|＿|＿|＿|＿|＿|\n" +
      "6|＿|＿|＿|＿|＿|＿|＿|＿|\n" +
      "7|♟|♟|♟|♟|♟|♟|♟|♟|\n" +
      "8|♜|♞|♝|♛|♚|♝|♞|♜|\n"
    "print chessboard as String after false move" in {
      tui.print() must be_==(expectedString)
    }
  }

  "A tui" should{
    val controller = new ChessController
    val tui = new tui(controller)
    tui.processInputLine("A2 A4")
    val expectedString = "\n" +
      "   A|B| C| D|E| F| G|H\n" +
      "1|♖|♘|♗|♕|♔|♗|♘|♖|\n" +
      "2|＿|♙|♙|♙|♙|♙|♙|♙|\n" +
      "3|＿|＿|＿|＿|＿|＿|＿|＿|\n" +
      "4|♙|＿|＿|＿|＿|＿|＿|＿|\n" +
      "5|＿|＿|＿|＿|＿|＿|＿|＿|\n" +
      "6|＿|＿|＿|＿|＿|＿|＿|＿|\n" +
      "7|♟|♟|♟|♟|♟|♟|♟|♟|\n" +
      "8|♜|♞|♝|♛|♚|♝|♞|♜|\n"
    "print chessboard as String after correct move" in {
      tui.print() must be_==(expectedString)
    }
  }

  "A tui" should{
    val controller = new ChessController
    val tui = new tui(controller)
    val printString = "\n" +
      "   A|B| C| D|E| F| G|H\n" +
      "1|♖|♘|♗|♕|♔|♗|♘|♖|\n" +
      "2|♙|♙|♙|♙|♙|♙|♙|♙|\n" +
      "3|＿|＿|＿|＿|＿|＿|＿|＿|\n" +
      "4|＿|＿|＿|＿|＿|＿|＿|＿|\n" +
      "5|＿|＿|＿|＿|＿|＿|＿|＿|\n" +
      "6|＿|＿|＿|＿|＿|＿|＿|＿|\n" +
      "7|♟|♟|♟|♟|♟|♟|♟|♟|\n" +
      "8|♜|♞|♝|♛|♚|♝|♞|♜|\n"
    "print chessboard as String" in {
      tui.print() must be_==(printString)
    }
  }


}