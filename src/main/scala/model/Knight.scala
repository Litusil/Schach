package model

import scala.collection.immutable.Vector

class Knight(color: Boolean, hasMoved: Boolean, position: (Int,Int)) extends ChessPiece(color,hasMoved,position) {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    var possibleMoves: Vector[(Int,Int)] = Vector()
    var KnightMoves: Vector[(Int,Int)] = Vector()

    KnightMoves =  KnightMoves :+(2,-1)
    KnightMoves =KnightMoves :+(2,1)
    KnightMoves =KnightMoves :+(1,-2)
    KnightMoves =KnightMoves:+(1,2)
    KnightMoves =KnightMoves:+(-1,-2)
    KnightMoves =KnightMoves:+(-1,2)
    KnightMoves =KnightMoves:+(-2,-1)
    KnightMoves =KnightMoves:+(-2,1)

    for (e <- KnightMoves){
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

  override def toString: String ={

    if (color) {
      return "\u2658"
    }
    "\u265E"

  }

}
