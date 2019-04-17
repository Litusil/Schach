package model

import scala.collection.immutable.Vector

case class King(override val color: Boolean,override val hasMoved: Boolean) extends ChessPiece(color,hasMoved) {

  override def getPossibleMoves(chessBoard: Vector[Vector[Option[ChessPiece]]]): Vector[(Int, Int)] = {
    val pos = this.getPosition(chessBoard)
    val kingMoves: Vector[(Int,Int)] = Vector((-1,0),(1,0),(0,1),(0,-1),(-1,1),(1,1),(-1,-1),(1,-1))
    var possibleMoves: Vector[(Int,Int)] = Vector()

    for (e <- kingMoves){
      val x = pos._2 + e._2
      val y = pos._1 + e._1
      if (x  >= 0 && x < chessBoard.length){
        if (y  >= 0 && y < chessBoard.length){
          if (chessBoard(y)(x).isEmpty|| chessBoard(y)(x).get.color != chessBoard(pos._1)(pos._2).get.color) {
            possibleMoves = possibleMoves :+ (y, x)
          }
        }
      }
    }
    possibleMoves
  }

  override def getPossibleAttacks(chessBoard: Vector[Vector[Option[ChessPiece]]]): Vector[(Int, Int)] = {
    getPossibleMoves(chessBoard)
  }

  override def updateMoved(): ChessPiece = {
    this.copy(hasMoved = true)
  }

  override def toString: String ={
    if (color) {
      return "\u2654"
    }
    "\u265A"
  }
}
