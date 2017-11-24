package model

class Queen(color : Boolean) extends ChessPiece {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Array[(Int, Int)] = {
    //not implemented
    return Array((0,0))
  }

  override def toString(): String ={
    "Q"
    /*
    if (color) {
      return "\u2655"
    }
    "\u265B"
    */
  }

}
