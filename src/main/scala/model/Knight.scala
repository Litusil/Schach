package model

import scala.collection.immutable.Vector

case class Knight(override val color: Boolean, moved: Boolean) extends ChessPiece(color,moved) {

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
          if (chessBoard(y)(x) == null || chessBoard(y)(x).color != chessBoard(pos._1)(pos._2).color) {
            possibleMoves = possibleMoves :+ (y, x)
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
