package model

class Knight(color : Boolean) extends ChessPiece {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Array[(Int, Int)] = {
    //not implemented
    return Array((0,0))
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
