package model

class Pawn(color : Boolean) extends ChessPiece {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Array[(Int, Int)] = {
    //not implemented
    return Array((0,0))
  }

  override def toString(): String ={
    if (color) {
      return "\u2659"
    }
    "\u265F"
  }
}
