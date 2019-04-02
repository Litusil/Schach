package model

abstract class ChessPiece(val color: Boolean, var hasMoved: Boolean) {

  def getPossibleMoves(chessBoard: Array[Array[Option[ChessPiece]]]): Vector[(Int, Int)]

  //determines the position of the Chesspiece
  def getPosition(chessBoard: Array[Array[Option[ChessPiece]]]): (Int,Int) = {
    for (y <- chessBoard.indices) {
      for (x <- chessBoard.indices) {
        if(!chessBoard(y)(x).isEmpty){
          if (chessBoard(y)(x).get eq this) {
          return (y, x)
          }
        }
      }
    }
    (-1, -1)
  }
}
