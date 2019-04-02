package model

import util.{Direction, MoveSetUtil}

import scala.collection.immutable.Vector

case class Rook(override val color: Boolean, moved: Boolean) extends ChessPiece(color,moved) {

  override def getPossibleMoves(chessBoard: Array[Array[Option[ChessPiece]]]): Vector[(Int, Int)] = {
    val pos = this.getPosition(chessBoard)
    var possibleMoves: Vector[(Int,Int)] = Vector()
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.LEFT,chessBoard)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.RIGHT,chessBoard)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.UP,chessBoard)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.DOWN,chessBoard)
    possibleMoves
  }
  override def toString: String = {
    if (color) {
      return "\u2656"
    }
    "\u265C"

  }

}
