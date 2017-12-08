package model

import scala.collection.immutable.Vector

class Knight(color: Boolean) extends ChessPiece(color) {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    val pos = this.getPosition(chessBoard)
    val possibleMoves: Vector[(Int,Int)] = Vector()

    val KnightMoves: Vector[(Int,Int)] = Vector()
    KnightMoves :+(2,-1) :+(2,1) :+(1,-2) :+(1,2) :+(-1,-2) :+(-1,2) :+(-2,-1) :+(-2,1)

    for (e <- KnightMoves){
      val x = pos._2 + e._2
      val y = pos._1 + e._1

      if (x  >= 0 && x < chessBoard.length){
        if (y  >= 0 && y < chessBoard.length){
          if (chessBoard(x)(y).color != chessBoard(pos._1)(pos._2).color) {
            possibleMoves :+ (x, y)
          }
        }
      }
    }
    possibleMoves
  }

  override def toString(): String ={

    if (color) {
      return "\u2658"
    }
    "\u265E"

  }

}
