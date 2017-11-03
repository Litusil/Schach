class Rook(color : Boolean) extends ChessPiece {

  override def getPossibleMoves(): Unit = {
    println("Moves")
  }
  override def toString(): String ={
    if (color) {
      return "\u2656"
    }
    "\u265C"
  }
}
