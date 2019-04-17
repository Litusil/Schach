package model

import scala.collection.immutable.Vector

case class Knight(override val color: Boolean,override val hasMoved: Boolean) extends ChessPiece(color,hasMoved) {

  override def getPossibleMoves(chessBoard: Vector[Vector[Option[ChessPiece]]]): Vector[(Int, Int)] = {
    val pos = this.getPosition(chessBoard)
    val KnightMoves: Vector[(Int,Int)] = Vector((2,-1),(2,1),(1,-2),(1,2),(-1,-2),(-1,2),(-2,-1),(-2,1))

    var possibleMoves: Vector[(Int,Int)] = Vector()

    for (e <- KnightMoves){
      val x = pos._2 + e._2
      val y = pos._1 + e._1

      if (x  >= 0 && x < chessBoard.length){
        if (y  >= 0 && y < chessBoard.length){
          if (chessBoard(y)(x).isEmpty || chessBoard(y)(x).get.color != chessBoard(pos._1)(pos._2).get.color) {
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
      return "\u2658"
    }
    "\u265E"

  }

}
