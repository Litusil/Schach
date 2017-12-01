package model

import scala.collection.immutable.Vector

class Pawn(color : Boolean) extends ChessPiece(color) {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    val pos = this.getPosition(chessBoard)
    val possibleMoves: Vector[(Int, Int)] = Vector()
    var yIncrementer = 1;

    if (!this.color) {
      yIncrementer = -1
    }

    if (chessBoard.length > pos._1 + yIncrementer){
      possibleMoves :+ (pos._1 + yIncrementer, pos._2)
    } else {
      return possibleMoves
    }
    if(chessBoard(pos._1 + yIncrementer)(pos._2 + 1) != null) {
      if (chessBoard(pos._1 + yIncrementer)(pos._2 + 1).color != this.color) {
        possibleMoves :+ chessBoard(pos._1 + yIncrementer)(pos._2 + 1)
      }
    }
    if(chessBoard(pos._1 + yIncrementer)(pos._2 - 1) != null) {
      if (chessBoard(pos._1 + yIncrementer)(pos._2 - 1).color != this.color) {
        possibleMoves :+ chessBoard(pos._1 + yIncrementer)(pos._2 - 1)
      }
    }

    if(!this.hasMoved) {
      if (this.color) {
        yIncrementer = 2
      } else {
        yIncrementer = -2
      }
      if (chessBoard(pos._1 + yIncrementer)(pos._2) == null){
        possibleMoves :+ (pos._1 + yIncrementer, pos._2)
      }
    }
    possibleMoves
  }


  override def toString(): String ={
    "P"

    /*
    if (color) {
      return "\u2659"
    }
    "\u265F"
    */
  }

}
