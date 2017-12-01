package model

import scala.collection.immutable.Vector

class Queen(color : Boolean) extends ChessPiece(color) {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    val pos = this.getPosition(chessBoard)
    val possibleMoves: Vector[(Int,Int)] = Vector()
    possibleMoves :+ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.LEFT,chessBoard)
    possibleMoves :+ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.RIGHT,chessBoard)
    possibleMoves :+ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.UP,chessBoard)
    possibleMoves :+ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.DOWN,chessBoard)
    possibleMoves :+ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.LEFT_UP,chessBoard)
    possibleMoves :+ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.RIGHT_UP,chessBoard)
    possibleMoves :+ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.LEFT_DOWN,chessBoard)
    possibleMoves :+ MoveSetUtil.getSelectableFields(pos._2, pos._1, Direction.RIGHT_DOWN,chessBoard)
    possibleMoves
  }

  override def toString(): String ={
    "Q"
    /*
    if (color) {
      return "\u2655"
    }
    "\u265B"
    */
  }

}
