package model

import scala.util.control.Breaks.{break, breakable}

abstract class ChessPiece {
  var hasMoved = false;

  def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Array[(Int, Int)]

  //def isMoveLegal(chessBoard: Array[Array[ChessPiece]]) : Boolean


  //determines the position of the Chesspiece
  def getPosition(chessBoard: Array[Array[ChessPiece]]): (Int,Int) = {
    for (i <- 0 to chessBoard.length - 1) {
      for (j <- 0 to chessBoard.length - 1) {
        if (chessBoard(i)(j) eq this) {

          return (i, j)
        }
      }
    }

    return (-1, -1)
  }


}
