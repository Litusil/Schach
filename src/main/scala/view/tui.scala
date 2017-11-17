package view

import controller.ChessController
import model.ChessPiece
import util.Observer

class tui(controller: ChessController) extends Observer {

  controller.add(this)
  var chessBoard: Array[Array[ChessPiece]] = controller.chessBoard
  print()

  override def update(): Unit ={
    chessBoard = controller.chessBoard
    print()
  }

  def print(): Unit ={
    for(i <- 0 to 7){
      for(j <- 0 to 7) {
        if(chessBoard(i)(j) != null) {
          System.out.format("%3s", chessBoard(i)(j).toString)
        } else {
          System.out.format("%3s","\u23f9")
        }
      }
      System.out.println("\n")
    }
  }


}
