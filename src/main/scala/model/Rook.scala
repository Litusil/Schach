package model

import util.{Direction, MoveSetUtil}

import scala.collection.immutable.Vector

case class Rook(override val color: Boolean,override val hasMoved: Boolean) extends ChessPiece(color,hasMoved) {

  override def getPossibleMoves(chessBoard: Vector[Vector[Option[ChessPiece]]]): Vector[(Int, Int)] = {
    val pos = this.getPosition(chessBoard)
    val possibleMoves: Vector[(Int,Int)] =
      (MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.LEFT,chessBoard)
    ++ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.RIGHT,chessBoard)
    ++ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.UP,chessBoard)
    ++ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.DOWN,chessBoard))

    possibleMoves
  }

  override def updateMoved(): ChessPiece = {
    this.copy(hasMoved = true)
  }

  override def toString: String = {
    if (color) {
      return "\u2656"
    }
    "\u265C"

  }

}
