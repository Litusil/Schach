package model

import util.Direction
import util.MoveSetUtil.getSelectableFields

import scala.collection.immutable.Vector

class Bishop(val color: Boolean,var hasMoved: Boolean, var position: (Int,Int)) extends ChessPiece() {

  override def getPossibleMoves(chessBoard: ChessBoard): Vector[(Int, Int)] = {
    var possibleMoves: Vector[(Int, Int)] = Vector()
    var ret: Vector[(Int,Int)] = Vector()
    possibleMoves = possibleMoves ++ getSelectableFields(position._2, position._1, Direction.LEFT_UP,chessBoard.board)
    possibleMoves = possibleMoves ++ getSelectableFields(position._2, position._1, Direction.RIGHT_UP,chessBoard.board)
    possibleMoves = possibleMoves ++ getSelectableFields(position._2, position._1, Direction.LEFT_DOWN,chessBoard.board)
    possibleMoves = possibleMoves ++ getSelectableFields(position._2, position._1, Direction.RIGHT_DOWN,chessBoard.board)
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
    getPossibleMoves(chessBoard)
  }

  override def toString: String ={
    if (color) {
      return "\u2657"
    }
    "\u265D"
  }
}
