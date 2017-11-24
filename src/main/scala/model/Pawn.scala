package model

import scala.collection.immutable.Vector

class Pawn(color : Boolean) extends ChessPiece(color) {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    val pos = this.getPosition(chessBoard)
    val possibleMoves: Vector[(Int,Int)] = Vector()
    var xIncrementer = 1;
    // TODO: IMPLEMENT!

    if(this.hasMoved == false){
      if(this.color){
        xIncrementer = 2
      } else {
        xIncrementer = -2
      }


    }



    possibleMoves


  }

  override def toString(): String ={
    if (color) {
      return "\u2659"
    }
    "\u265F"
  }
}
