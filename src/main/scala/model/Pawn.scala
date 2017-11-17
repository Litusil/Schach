package model

class Pawn(color : Boolean) extends ChessPiece {

  override def getPossibleMoves(): Unit = {



    println("Moves")
  }

  override def toString(): String ={
    if (color) {
      return "\u2659"
    }
    "\u265F"
  }
}
