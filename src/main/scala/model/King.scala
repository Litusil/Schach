package model

class King(color: Boolean) extends ChessPiece(color) {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    //not implemented
    return Vector((0,0))
  }

  override def toString(): String ={
    "K"
    /*
    if (color) {
      return "\u2654"
    }
    "\u265A"
    */
  }
}
