package model

import util.{Direction, MoveSetUtil}

import scala.collection.immutable.Vector

class Rook(color: Boolean, hasMoved: Boolean, position: (Int,Int)) extends ChessPiece(color,hasMoved,position) {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    var possibleMoves: Vector[(Int,Int)] = Vector()
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(position._2, position._1, Direction.LEFT,chessBoard)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(position._2, position._1, Direction.RIGHT,chessBoard)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(position._2, position._1, Direction.UP,chessBoard)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(position._2, position._1, Direction.DOWN,chessBoard)
    possibleMoves
  }

  override def getPossibleAttacks(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    getPossibleMoves(chessBoard)
  }

  override def toString: String = {
    if (color) {
      return "\u2656"
    }
    "\u265C"

  }

}
