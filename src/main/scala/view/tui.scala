package view

import controller.ChessController
import model.ChessPiece
import util.Observer


class tui(controller: ChessController) extends Observer {

  controller.add(this)
  var chessBoard: Array[Array[ChessPiece]] = controller.chessBoard

  override def update(): Unit ={
    chessBoard = controller.chessBoard
    print()
  }

  def print(): Unit ={
    val lineseparator =  "|＿" * 8 + "\n"
    val line = "|x" * 8 + "|\n"
    var board =  "\n" + (line) * 8

    for(i <- 0 to 7){
      for(j <- 0 to 7) {
        if(chessBoard(i)(j) != null) {
          board = board.replaceFirst("x",chessBoard(i)(j).toString)
        } else {
          board = board.replaceFirst("x","＿")
        }
      }
    }
    println(board)
  }

  def processInputLine(eingabe: String): Unit ={
    var command = eingabe;
    command = command.replaceAll("A","0")
    command = command.replaceAll("B","0")
    command = command.replaceAll("C","0")
    command = command.replaceAll("D","0")
    command = command.replaceAll("E","0")
    command = command.replaceAll("F","0")
    command = command.replaceAll("G","0")
    command = command.replaceAll("H","0")
    controller.move(command(0).toInt-48,command(1).toInt-49,command(3).toInt-48,command(4).toInt-49)
  }

}
