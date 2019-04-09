package model

import util.Direction
import util.MoveSetUtil.getSelectableFields

import scala.collection.immutable.Vector

case class Bishop(override val color : Boolean, override val hasMoved : Boolean) extends ChessPiece(color, hasMoved) {

  override def getPossibleMoves(chessBoard: Vector[Vector[Option[ChessPiece]]]): Vector[(Int, Int)] = {
    val pos = this.getPosition(chessBoard)
    var possibleMoves: Vector[(Int, Int)] = (getSelectableFields(pos._2, pos._1, Direction.LEFT_UP,chessBoard)
    ++ getSelectableFields(pos._2, pos._1, Direction.RIGHT_UP,chessBoard)
    ++ getSelectableFields(pos._2, pos._1, Direction.LEFT_DOWN,chessBoard)
    ++ getSelectableFields(pos._2, pos._1, Direction.RIGHT_DOWN,chessBoard))
    possibleMoves
  }

  override def updateMoved(): ChessPiece = {
    this.copy(hasMoved = true)
  }

  override def toString: String ={
    if (color) {
      return "\u2657"
    }
    "\u265D"
  }
}
