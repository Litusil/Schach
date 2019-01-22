package model

import scala.collection.immutable.Vector

class Knight(val color: Boolean,var hasMoved: Boolean, var position: (Int,Int)) extends ChessPiece() {

  override def getPossibleMoves(chessBoard:ChessBoard): Vector[(Int, Int)] = {
    var possibleMoves: Vector[(Int,Int)] = Vector()
    var ret: Vector[(Int,Int)] = Vector()
    var KnightMoves: Vector[(Int,Int)] = Vector()

    if(this.position == (-1,-1)){
      return possibleMoves
    }

    KnightMoves =  KnightMoves :+(2,-1)
    KnightMoves =KnightMoves :+(2,1)
    KnightMoves =KnightMoves :+(1,-2)
    KnightMoves =KnightMoves:+(1,2)
    KnightMoves =KnightMoves:+(-1,-2)
    KnightMoves =KnightMoves:+(-1,2)
    KnightMoves =KnightMoves:+(-2,-1)
    KnightMoves =KnightMoves:+(-2,1)

    for (e <- KnightMoves){
      val x = position._2 + e._2
      val y = position._1 + e._1

      if (x  >= 0 && x < chessBoard.board.length){
        if (y  >= 0 && y < chessBoard.board.length){
          if (chessBoard.board(y)(x) == null || chessBoard.board(y)(x).color != chessBoard.board(position._1)(position._2).color) {
            possibleMoves = possibleMoves :+ (y, x)
          }
        }
      }
    }
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
      return "\u2658"
    }
    "\u265E"

  }

}
