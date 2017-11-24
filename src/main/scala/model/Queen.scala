package model

class Queen(color : Boolean) extends ChessPiece(color) {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    //not implemented
    return Vector((0,0))
  }

  override def toString(): String ={
    if (color) {
      return "\u2655"
    }
    "\u265B"
  }
}
