package model

import scala.collection.immutable.Vector

case class King(override val color: Boolean,var hasMoved: Boolean,override var position: (Int,Int)) extends ChessPiece(color,hasMoved,position) {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    var possibleMoves: Vector[(Int,Int)] = Vector()
    var kingMoves: Vector[(Int,Int)] = Vector()
    kingMoves = kingMoves :+(-1,0)
    kingMoves = kingMoves:+(1,0)
    kingMoves = kingMoves:+(0,1)
    kingMoves = kingMoves:+(0,-1)
    kingMoves = kingMoves:+(-1,1)
    kingMoves = kingMoves:+(1,1)
    kingMoves = kingMoves:+(-1,-1)
    kingMoves = kingMoves:+(1,-1)
    for (e <- kingMoves){
      val x = position._2 + e._2
      val y = position._1 + e._1
      if (x  >= 0 && x < chessBoard.length){
        if (y  >= 0 && y < chessBoard.length){
          if (chessBoard(y)(x) == null || chessBoard(y)(x).color != chessBoard(position._1)(position._2).color) {
            possibleMoves = possibleMoves :+ (y, x)
          }
        }
      }
    }
    possibleMoves
  }

  override def getPossibleAttacks(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    getPossibleMoves(chessBoard)
  }

  def isCastlingPossible() (chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    var possibleCastleMoves: Vector[(Int,Int)] = Vector()
    if(!this.hasMoved){

    }
    possibleCastleMoves
  }

  override def toString: String ={
    if (color) {
      return "\u2654"
    }
    "\u265A"
  }
}
