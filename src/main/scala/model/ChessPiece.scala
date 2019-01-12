package model

abstract class ChessPiece(val color: Boolean,var hasMoved: Boolean,var position: (Int,Int)) {

  def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)]

  def getPossibleAttacks(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)]

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
