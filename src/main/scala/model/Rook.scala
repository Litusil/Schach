package model

import util.{Direction, MoveSetUtil}

import scala.collection.immutable.Vector

case class Rook(override val color: Boolean, moved: Boolean) extends ChessPiece(color,moved) {

  override def getPossibleMoves(chessBoard: Array[Array[Option[ChessPiece]]]): Vector[(Int, Int)] = {
    val pos = this.getPosition(chessBoard)
    val possibleMoves: Vector[(Int,Int)] =
      (MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.LEFT,chessBoard)
    ++ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.RIGHT,chessBoard)
    ++ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.UP,chessBoard)
    ++ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.DOWN,chessBoard))

    possibleMoves
  }
  override def toString: String = {
    if (color) {
      return "\u2656"
    }
    "\u265C"

  }

}
