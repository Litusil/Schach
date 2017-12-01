package model

class Knight(color: Boolean) extends ChessPiece(color) {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    //not implemented
    return Vector((0,0))
  }

  override def toString(): String ={
    "N"
    /*
    if (color) {
      return "\u2658"
    }
    "\u265E"
    */
  }

}
