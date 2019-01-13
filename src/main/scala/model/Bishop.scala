package model

import util.Direction
import util.MoveSetUtil.getSelectableFields

import scala.collection.immutable.Vector

class Bishop(val color: Boolean,var hasMoved: Boolean, var position: (Int,Int)) extends ChessPiece() {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    var possibleMoves: Vector[(Int, Int)] = Vector()
    possibleMoves = possibleMoves ++ getSelectableFields(position._2, position._1, Direction.LEFT_UP,chessBoard)
    possibleMoves = possibleMoves ++ getSelectableFields(position._2, position._1, Direction.RIGHT_UP,chessBoard)
    possibleMoves = possibleMoves ++ getSelectableFields(position._2, position._1, Direction.LEFT_DOWN,chessBoard)
    possibleMoves = possibleMoves ++ getSelectableFields(position._2, position._1, Direction.RIGHT_DOWN,chessBoard)
    possibleMoves
  }

  override def getPossibleAttacks(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    getPossibleMoves(chessBoard)
  }

  override def toString: String ={
    if (color) {
      return "\u2657"
    }
    "\u265D"
  }
}
