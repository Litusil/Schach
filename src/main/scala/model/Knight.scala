package model

import scala.collection.immutable.Vector

case class Knight(override val color: Boolean) extends ChessPiece(color) {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    val pos = this.getPosition(chessBoard)
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
      val x = pos._2 + e._2
      val y = pos._1 + e._1

      if (x  >= 0 && x < chessBoard.length){
        if (y  >= 0 && y < chessBoard.length){
          if (chessBoard(x)(y) == null || chessBoard(x)(y).color != chessBoard(pos._1)(pos._2).color) {
            possibleMoves = possibleMoves :+ (x, y)
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
