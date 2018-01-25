package model

import model.MoveSetUtil.getSelectableFields

import scala.collection.immutable.Vector

case class Bishop(override val color : Boolean, moved: Boolean) extends ChessPiece(color, moved) {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    val pos = this.getPosition(chessBoard)
    var possibleMoves: Vector[(Int, Int)] = Vector()
    possibleMoves = possibleMoves ++ getSelectableFields(pos._2, pos._1, Direction.LEFT_UP,chessBoard)
    possibleMoves = possibleMoves ++ getSelectableFields(pos._2, pos._1, Direction.RIGHT_UP,chessBoard)
    possibleMoves = possibleMoves ++ getSelectableFields(pos._2, pos._1, Direction.LEFT_DOWN,chessBoard)
    possibleMoves = possibleMoves ++ getSelectableFields(pos._2, pos._1, Direction.RIGHT_DOWN,chessBoard)
    possibleMoves
  }

  override def toString: String ={
    if (color) {
      return "\u2657"
    }
    "\u265D"
  }
}
