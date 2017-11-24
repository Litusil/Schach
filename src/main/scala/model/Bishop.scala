package model

class Bishop(color : Boolean) extends ChessPiece {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Array[(Int, Int)] = {
    //not implemented
    return Array((0,0))
  }

  override def toString(): String ={
    if (color) {
      return "\u2657"
    }
    "\u265D"
  }
}