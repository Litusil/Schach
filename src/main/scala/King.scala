class King(color : Boolean) extends ChessPiece {

  override def getPossibleMoves(): Unit = {
    println("Moves")
  }

  override def toString(): String ={
    if (color) {
      return "\u2654"
    }
    "\u265A"
  }
}
