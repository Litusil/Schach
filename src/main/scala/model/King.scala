package model

import scala.collection.immutable.Vector

case class King(override val color: Boolean, var moved: Boolean) extends ChessPiece(color,moved) {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    val pos = this.getPosition(chessBoard)
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



  override def toString: String ={
    if (color) {
      return "\u2654"
    }
    "\u265A"
  }
}
