package model

class Bishop(color : Boolean) extends ChessPiece {

  override def getPossibleMoves(): Unit = {
    println("Moves")
  }

  override def toString(): String ={
    if (color) {
      return "\u2657"
    }
    "\u265D"
  }
}
