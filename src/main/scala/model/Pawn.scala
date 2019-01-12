package model

import scala.collection.immutable.Vector

case class Pawn(override val color : Boolean,  moved: Boolean, var pos: (Int,Int)) extends ChessPiece(color,moved,pos) {

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
    if (xSchlagen < chessBoard.length && (pos._1 + yIncrementer) < chessBoard.length){
      if(chessBoard(pos._1 + yIncrementer)(pos._2 + 1) != null) {
        if (chessBoard(pos._1 + yIncrementer)(pos._2 + 1).color != this.color) {
          possibleMoves = possibleMoves :+ (pos._1 + yIncrementer, pos._2 + 1)
        }
      }
    }
    xSchlagen = pos._2 - 1
    if(xSchlagen >= 0 && pos._1 + yIncrementer < chessBoard.length) {
      if(chessBoard(pos._1 + yIncrementer)(pos._2 - 1) != null) {
        if (chessBoard(pos._1 + yIncrementer)(pos._2 - 1).color != this.color) {
          possibleMoves = possibleMoves :+ (pos._1 + yIncrementer, pos._2 - 1)
        }
      }
    }

    if(!this.hasMoved) {
      if (this.color) {
        if ((pos._1 + 1) < chessBoard.length && chessBoard(pos._1 + 1)(pos._2) != null) {
          return possibleMoves
        }
        yIncrementer = 2
      } else {
        if ((pos._1 - 1) < chessBoard.length && chessBoard(pos._1 - 1)(pos._2) != null) {
          return possibleMoves
        }
        yIncrementer = -2
      }
        if (pos._1 + yIncrementer < chessBoard.length && chessBoard(pos._1 + yIncrementer)(pos._2) == null) {
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
