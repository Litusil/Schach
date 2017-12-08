package model

import scala.collection.immutable.Vector

class Bishop(color : Boolean) extends ChessPiece(color) {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    val pos = this.getPosition(chessBoard)
    val possibleMoves: Vector[(Int,Int)] = Vector()
    possibleMoves :+ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.LEFT_UP,chessBoard)
    possibleMoves :+ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.RIGHT_UP,chessBoard)
    possibleMoves :+ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.LEFT_DOWN,chessBoard)
    possibleMoves :+ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.RIGHT_DOWN,chessBoard)
    possibleMoves
  }

  override def toString(): String ={
    if (color) {
      return "\u2657"
    }
    "\u265D"

  }
}
