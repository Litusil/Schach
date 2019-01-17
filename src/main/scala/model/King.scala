package model

import scala.collection.immutable.Vector

case class King(val color: Boolean,var hasMoved: Boolean, var position: (Int,Int)) extends ChessPiece() {

  override def getPossibleMoves(chessBoard: ChessBoard): Vector[(Int, Int)] = {
    var possibleMoves: Vector[(Int,Int)] = Vector()
    var kingMoves: Vector[(Int,Int)] = Vector()
    var ret: Vector[(Int,Int)] = Vector()
    kingMoves = kingMoves :+(-1,0)
    kingMoves = kingMoves:+(1,0)
    kingMoves = kingMoves:+(0,1)
    kingMoves = kingMoves:+(0,-1)
    kingMoves = kingMoves:+(-1,1)
    kingMoves = kingMoves:+(1,1)
    kingMoves = kingMoves:+(-1,-1)
    kingMoves = kingMoves:+(1,-1)
    for (e <- kingMoves){
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

  def isCastlingPossible() (chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    var possibleCastleMoves: Vector[(Int,Int)] = Vector()
    if(!this.hasMoved){

    }
    possibleCastleMoves
  }

  override def toString: String ={
    if (color) {
      return "\u2654"
    }
    "\u265A"
  }
}
