package model

class Knight(color : Boolean) extends ChessPiece {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Unit = {
    println("Moves")
  }

  override def toString(): String ={
    if (color) {
      return "\u2658"
    }
    "\u265E"
  }
}
