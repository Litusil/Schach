package model

import scala.collection.immutable.Vector

class Pawn(val color: Boolean,var hasMoved: Boolean, var position: (Int,Int)) extends ChessPiece() {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    var possibleMoves: Vector[(Int, Int)] = Vector()
    var yIncrementer = 1

    if (!this.color) {
      yIncrementer = -1
    }

    if (chessBoard.length > position._1 + yIncrementer && chessBoard(position._1 + yIncrementer)(position._2) == null){
      possibleMoves = possibleMoves :+ (position._1 + yIncrementer, position._2)
    }
    
    var xSchlagen = position._2 + 1
    if (xSchlagen < chessBoard.length && (position._1 + yIncrementer) < chessBoard.length){
      if(chessBoard(position._1 + yIncrementer)(position._2 + 1) != null) {
        if (chessBoard(position._1 + yIncrementer)(position._2 + 1).color != color) {
          possibleMoves = possibleMoves :+ (position._1 + yIncrementer, position._2 + 1)
        }
      }
    }
    xSchlagen = position._2 - 1
    if(xSchlagen >= 0 && position._1 + yIncrementer < chessBoard.length) {
      if(chessBoard(position._1 + yIncrementer)(position._2 - 1) != null) {
        if (chessBoard(position._1 + yIncrementer)(position._2 - 1).color != color) {
          possibleMoves = possibleMoves :+ (position._1 + yIncrementer, position._2 - 1)
        }
      }
    }

    if(!this.hasMoved) {
      if (this.color) {
        if ((position._1 + 1) < chessBoard.length && chessBoard(position._1 + 1)(position._2) != null) {
          return possibleMoves
        }
        yIncrementer = 2
      } else {
        if ((position._1 - 1) < chessBoard.length && chessBoard(position._1 - 1)(position._2) != null) {
          return possibleMoves
        }
        yIncrementer = -2
      }
        if (position._1 + yIncrementer < chessBoard.length && chessBoard(position._1 + yIncrementer)(position._2) == null) {
          possibleMoves = possibleMoves :+ (position._1 + yIncrementer, position._2)
        }
      }

    possibleMoves
  }

  override def getPossibleAttacks(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    var possibleAttacks: Vector[(Int, Int)] = Vector()
    var yIncrementer = 1

    if (!this.color) {
      yIncrementer = -1
    }

    if (position._2 + 1 < chessBoard.length && (position._1 + yIncrementer) < chessBoard.length){
        val pos1 = chessBoard(position._1 + yIncrementer)(position._2 + 1)
        if (pos1 == null || pos1.color != color  ) {
          possibleAttacks = possibleAttacks :+ (position._1 + yIncrementer, position._2 + 1)
        }
    }

    if(position._2 - 1 >= 0 && position._1 + yIncrementer < chessBoard.length ) {
        val pos2 = chessBoard(position._1 + yIncrementer)(position._2 - 1)
        if (pos2 == null || pos2.color != color ) {
          possibleAttacks = possibleAttacks :+ (position._1 + yIncrementer, position._2 - 1)
        }
      }

    possibleAttacks
  }

  override def toString: String ={
    if (color) {
      return "\u2659"
    }
    "\u265F"
  }

}
