package model

abstract class ChessPiece(val color: Boolean, var hasMoved: Boolean) {

  def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)]

  //def isMoveLegal(chessBoard: Array[Array[ChessPiece]]) : Boolean


  //determines the position of the Chesspiece
  def getPosition(chessBoard: Array[Array[ChessPiece]]): (Int,Int) = {
    for (y <- chessBoard.indices) {
      for (x <- chessBoard.indices) {
        if (chessBoard(y)(x) eq this) {

          return (y, x)
        }
      }
    }

    (-1, -1)
  }
}
