package model

class Knight(color : Boolean) extends ChessPiece {

  override def getPossibleMoves(): Unit = {
    println("Moves")
  }

  override def toString(): String ={
    if (color) {
      return "\u2658"
    }
    "\u265E"
  }
}
