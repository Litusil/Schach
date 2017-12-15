package model

import scala.collection.immutable.Vector

case class Queen(override val color : Boolean) extends ChessPiece(color) {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    val pos = this.getPosition(chessBoard)
    var possibleMoves: Vector[(Int,Int)] = Vector()
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.LEFT,chessBoard)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.RIGHT,chessBoard)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.UP,chessBoard)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.DOWN,chessBoard)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.LEFT_UP,chessBoard)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.RIGHT_UP,chessBoard)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.LEFT_DOWN,chessBoard)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.RIGHT_DOWN,chessBoard)
    possibleMoves
  }

  override def toString(): String ={
    if (color) {
      return "\u2655"
    }
    "\u265B"

  }

}
