package model

import scala.collection.immutable.Vector

class King(color: Boolean) extends ChessPiece(color) {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    val pos = this.getPosition(chessBoard)
    val possibleMoves: Vector[(Int,Int)] = Vector()

    val KingMoves: Vector[(Int,Int)] = Vector()
    KingMoves :+(-1,0) :+(1,0) :+(0,1) :+(0,-1) :+(-1,1) :+(1,1) :+(-1,-1) :+(1,-1)

    for (e <- KingMoves){
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
      return "\u2654"
    }
    "\u265A"
  }
}
