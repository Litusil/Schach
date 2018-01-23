package model

import scala.collection.immutable.Vector

case class Pawn(override val color : Boolean,  moved: Boolean) extends ChessPiece(color,moved) {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    val pos = this.getPosition(chessBoard)
    var possibleMoves: Vector[(Int, Int)] = Vector()
    var yIncrementer = 1

    if (!this.color) {
      yIncrementer = -1
    }

    if (chessBoard.length > pos._1 + yIncrementer && chessBoard(pos._1 + yIncrementer)(pos._2) == null){
      possibleMoves = possibleMoves :+ (pos._1 + yIncrementer, pos._2)
    }
    
    var xSchlagen = pos._2 + 1
    if (xSchlagen < 8){
      if(chessBoard(pos._1 + yIncrementer)(pos._2 + 1) != null) {
        if (chessBoard(pos._1 + yIncrementer)(pos._2 + 1).color != this.color) {
          possibleMoves = possibleMoves :+ (pos._1 + yIncrementer, pos._2 + 1)
        }
      }
    }
    xSchlagen = pos._2 - 1
    if(xSchlagen >= 0) {
      if(chessBoard(pos._1 + yIncrementer)(pos._2 - 1) != null) {
        if (chessBoard(pos._1 + yIncrementer)(pos._2 - 1).color != this.color) {
          possibleMoves = possibleMoves :+ (pos._1 + yIncrementer, pos._2 - 1)
        }
      }
    }

    if(!this.hasMoved) {
      if (this.color) {
        yIncrementer = 2
      } else {
        yIncrementer = -2
      }
      if (chessBoard(pos._1 + yIncrementer)(pos._2) == null){
        possibleMoves = possibleMoves :+ (pos._1 + yIncrementer, pos._2)
      }
    }

    possibleMoves
  }


  override def toString: String ={
    if (color) {
      return "\u2659"
    }
    "\u265F"
  }

}
