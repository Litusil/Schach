package model

abstract class ChessPiece(val color: Boolean,val hasMoved: Boolean) {

  def getPossibleMoves(chessBoard: Vector[Vector[Option[ChessPiece]]]): Vector[(Int, Int)]

  def updateMoved(): ChessPiece

  //determines the position of the Chesspiece
  def getPosition(chessBoard: Vector[Vector[Option[ChessPiece]]]): (Int,Int) = {
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
