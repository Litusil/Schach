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
    //val lineseparator =  "+---" * 8 + "+\n"
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
}
