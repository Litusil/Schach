package model

import scala.util.control.Breaks.{break, breakable}

abstract class ChessPiece(val color: Boolean) {
  var hasMoved = false;

  def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)]

  //def isMoveLegal(chessBoard: Array[Array[ChessPiece]]) : Boolean


  //determines the position of the Chesspiece
  def getPosition(chessBoard: Array[Array[ChessPiece]]): (Int,Int) = {
    for (y <- 0 to chessBoard.length - 1) {
      for (x <- 0 to chessBoard.length - 1) {
        if (chessBoard(y)(x) eq this) {

          return (y, x)
        }
      }
    }

    return (-1, -1)
  }
}
