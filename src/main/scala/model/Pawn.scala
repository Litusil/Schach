package model

import scala.collection.immutable.Vector

class Pawn(val color: Boolean,var hasMoved: Boolean, var position: (Int,Int)) extends ChessPiece() {

  override def getPossibleMoves(chessBoard: ChessBoard): Vector[(Int, Int)] = {
    var possibleMoves: Vector[(Int, Int)] = Vector()
    var ret: Vector[(Int,Int)] = Vector()
    var yIncrementer = 1

    if(this.position == (-1,-1)){
      return possibleMoves
    }

    if (!this.color) {
      yIncrementer = -1
    }

    if (chessBoard.board.length > position._1 + yIncrementer && chessBoard.board(position._1 + yIncrementer)(position._2) == null){
      possibleMoves = possibleMoves :+ (position._1 + yIncrementer, position._2)
    }
    
    var xSchlagen = position._2 + 1
    if (xSchlagen < chessBoard.board.length && (position._1 + yIncrementer) < chessBoard.board.length){
      if(chessBoard.board(position._1 + yIncrementer)(position._2 + 1) != null) {
        if (chessBoard.board(position._1 + yIncrementer)(position._2 + 1).color != color) {
          possibleMoves = possibleMoves :+ (position._1 + yIncrementer, position._2 + 1)
        }
      }
    }
    xSchlagen = position._2 - 1
    if(xSchlagen >= 0 && position._1 + yIncrementer < chessBoard.board.length) {
      if(chessBoard.board(position._1 + yIncrementer)(position._2 - 1) != null) {
        if (chessBoard.board(position._1 + yIncrementer)(position._2 - 1).color != color) {
          possibleMoves = possibleMoves :+ (position._1 + yIncrementer, position._2 - 1)
        }
      }
    }

    if(!this.hasMoved) {
      if (this.color) {
        if ((position._1 + 1) < chessBoard.board.length && chessBoard.board(position._1 + 1)(position._2) != null) {
          return possibleMoves
        }
        yIncrementer = 2
      } else {
        if ((position._1 - 1) < chessBoard.board.length && chessBoard.board(position._1 - 1)(position._2) != null) {
          return possibleMoves
        }
        yIncrementer = -2
      }
        if (position._1 + yIncrementer < chessBoard.board.length && chessBoard.board(position._1 + yIncrementer)(position._2) == null) {
          possibleMoves = possibleMoves :+ (position._1 + yIncrementer, position._2)
        }
      }

    if(possibleMoves.length == 0){
      return ret
    }

    if(!chessBoard.simulated){
      for(y<- possibleMoves){
        val test = chessBoard.simulate(this.position._2,this.position._1,y._2,y._1)
        if(chessBoard.currentPlayer){
          if(!test.isWhiteCheck()){
            ret = ret :+ (y._1,y._2)
          }
        } else {
          if(!test.isBlackCheck()){
            ret = ret :+ (y._1,y._2)
          }
        }
      }
      return ret
    }
    ret = ret ++ possibleMoves
    ret
  }

  override def getPossibleAttacks(chessBoard: ChessBoard): Vector[(Int, Int)] = {
    var possibleAttacks: Vector[(Int, Int)] = Vector()
    var yIncrementer = 1

    if(this.position == (-1,-1)){
      return possibleAttacks
    }

    if (!this.color) {
      yIncrementer = -1
    }

    if (position._2 + 1 < chessBoard.board.length && (position._1 + yIncrementer) < chessBoard.board.length){
        val pos1 = chessBoard.board(position._1 + yIncrementer)(position._2 + 1)
        if (pos1 == null || pos1.color != color  ) {
          possibleAttacks = possibleAttacks :+ (position._1 + yIncrementer, position._2 + 1)
        }
    }

    if(position._2 - 1 >= 0 && position._1 + yIncrementer < chessBoard.board.length ) {
        val pos2 = chessBoard.board(position._1 + yIncrementer)(position._2 - 1)
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
