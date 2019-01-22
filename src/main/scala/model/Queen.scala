package model

import util.{Direction, MoveSetUtil}

import scala.collection.immutable.Vector

class Queen(val color: Boolean,var hasMoved: Boolean, var position: (Int,Int)) extends ChessPiece() {

  override def getPossibleMoves(chessBoard:ChessBoard): Vector[(Int, Int)] = {
    var possibleMoves: Vector[(Int,Int)] = Vector()
    var ret: Vector[(Int,Int)] = Vector()

    if(this.position == (-1,-1)){
      return possibleMoves
    }

    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(position._2, position._1, Direction.LEFT,chessBoard.board)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(position._2, position._1, Direction.RIGHT,chessBoard.board)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(position._2, position._1, Direction.UP,chessBoard.board)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(position._2, position._1, Direction.DOWN,chessBoard.board)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(position._2, position._1, Direction.LEFT_UP,chessBoard.board)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(position._2, position._1, Direction.RIGHT_UP,chessBoard.board)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(position._2, position._1, Direction.LEFT_DOWN,chessBoard.board)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(position._2, position._1, Direction.RIGHT_DOWN,chessBoard.board)

    if(possibleMoves.length == 0){
      return ret
    }
    if(!chessBoard.simulated){
      for(y<- possibleMoves){
        val test = chessBoard.simulate(this.position._2,this.position._1,y._2,y._1)
        if(chessBoard.currentPlayer){
          if(!test.isWhiteCheck()){
            ret = ret :+ (y._1,y._2)
          }
        } else {
          if(!test.isBlackCheck()){
            ret = ret :+ (y._1,y._2)
          }
        }
      }
      return ret
    }
    ret = ret ++ possibleMoves
    ret
  }

  override def getPossibleAttacks(chessBoard: ChessBoard): Vector[(Int, Int)] = {
    getPossibleMoves(chessBoard)
  }

  override def toString: String ={
    if (color) {
      return "\u2655"
    }
    "\u265B"
  }

}
