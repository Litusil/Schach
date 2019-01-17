package model

import util.{Direction, MoveSetUtil}

import scala.collection.immutable.Vector

class Rook(val color: Boolean,var hasMoved: Boolean, var position: (Int,Int)) extends ChessPiece() {

  override def getPossibleMoves(chessBoard: ChessBoard): Vector[(Int, Int)] = {
    var ret: Vector[(Int,Int)] = Vector()
    var possibleMoves: Vector[(Int,Int)] = Vector()
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(position._2, position._1, Direction.LEFT,chessBoard.board)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(position._2, position._1, Direction.RIGHT,chessBoard.board)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(position._2, position._1, Direction.UP,chessBoard.board)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(position._2, position._1, Direction.DOWN,chessBoard.board)

    if(possibleMoves.length == 0){
      return ret
    }

    if(!chessBoard.simulated){
      if(this.color == chessBoard.currentPlayer && chessBoard.whiteCheck){
        for(y<- possibleMoves){
          val test = chessBoard.simulate(this.position._2,this.position._1,y._2,y._1)
          if(!test.isWhiteCheck()){
            ret = ret :+ (y._1,y._2)
          }
        }
        return ret
      }
      if(this.color == chessBoard.currentPlayer && chessBoard.blackCheck){
        for(y<- possibleMoves){
          val test = chessBoard.simulate(this.position._2,this.position._1,y._2,y._1)
          if(!test.isBlackCheck()){
            ret = ret :+ (y._1,y._2)
          }
        }
        return ret
      }
    }
    ret = ret ++ possibleMoves
    ret
  }

  override def getPossibleAttacks(chessBoard: ChessBoard): Vector[(Int, Int)] = {
    var possibleMoves: Vector[(Int,Int)] = Vector()
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(position._2, position._1, Direction.LEFT,chessBoard.board)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(position._2, position._1, Direction.RIGHT,chessBoard.board)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(position._2, position._1, Direction.UP,chessBoard.board)
    possibleMoves = possibleMoves ++ MoveSetUtil.getSelectableFields(position._2, position._1, Direction.DOWN,chessBoard.board)
    possibleMoves
  }

  override def toString: String = {
    if (color) {
      return "\u2656"
    }
    "\u265C"

  }

}
