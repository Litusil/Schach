package model

import scala.collection.immutable.Vector

case class Pawn(override val color : Boolean, override val hasMoved: Boolean) extends ChessPiece(color,hasMoved) {

  override def getPossibleMoves(chessBoard: Vector[Vector[Option[ChessPiece]]]): Vector[(Int, Int)] = {
    val pos = this.getPosition(chessBoard)
    var possibleMoves: Vector[(Int, Int)] = Vector()
    var yIncrementer = 1

    if (!this.color) {
      yIncrementer = -1
    }

    if (chessBoard.length > pos._1 + yIncrementer && chessBoard(pos._1 + yIncrementer)(pos._2).isEmpty){
      possibleMoves = possibleMoves :+ (pos._1 + yIncrementer, pos._2)
    }
    
    var xSchlagen = pos._2 + 1
    if (xSchlagen < chessBoard.length && (pos._1 + yIncrementer) < chessBoard.length){
      if(!chessBoard(pos._1 + yIncrementer)(pos._2 + 1).isEmpty) {
        if (chessBoard(pos._1 + yIncrementer)(pos._2 + 1).get.color != this.color) {
          possibleMoves = possibleMoves :+ (pos._1 + yIncrementer, pos._2 + 1)
        }
      }
    }
    xSchlagen = pos._2 - 1
    if(xSchlagen >= 0 && pos._1 + yIncrementer < chessBoard.length) {
      if(!chessBoard(pos._1 + yIncrementer)(pos._2 - 1).isEmpty) {
        if (chessBoard(pos._1 + yIncrementer)(pos._2 - 1).get.color != this.color) {
          possibleMoves = possibleMoves :+ (pos._1 + yIncrementer, pos._2 - 1)
        }
      }
    }

    if(!this.hasMoved) {
      if (this.color) {
        if ((pos._1 + 1) < chessBoard.length && !chessBoard(pos._1 + 1)(pos._2).isEmpty) {
          return possibleMoves
        }
        yIncrementer = 2
      } else {
        if ((pos._1 - 1) < chessBoard.length && !chessBoard(pos._1 - 1)(pos._2).isEmpty) {
          return possibleMoves
        }
        yIncrementer = -2
      }
        if (pos._1 + yIncrementer < chessBoard.length && chessBoard(pos._1 + yIncrementer)(pos._2).isEmpty) {
          possibleMoves = possibleMoves :+ (pos._1 + yIncrementer, pos._2)
        }
      }

    possibleMoves
  }

  override def getPossibleAttacks(chessBoard:  Vector[Vector[Option[ChessPiece]]]): Vector[(Int, Int)] = {
    var possibleAttacks: Vector[(Int, Int)] = Vector()
    val pos = this.getPosition(chessBoard)
    var yIncrementer = 1

    if(pos == (-1,-1)){
      return possibleAttacks
    }

    if (!this.color) {
      yIncrementer = -1
    }

    if (pos._2 + 1 < chessBoard.length && (pos._1 + yIncrementer) < chessBoard.length){
      val pos1 = chessBoard(pos._1 + yIncrementer)(pos._2 + 1)
      if (pos1.isEmpty || pos1.get.color != color) {
        possibleAttacks = possibleAttacks :+ (pos._1 + yIncrementer, pos._2 + 1)
      }
    }

    if(pos._2 - 1 >= 0 && pos._1 + yIncrementer < chessBoard.length) {
      val pos2 = chessBoard(pos._1 + yIncrementer)(pos._2 - 1)
      if (pos2.isEmpty || pos2.get.color != color ) {
        possibleAttacks = possibleAttacks :+ (pos._1 + yIncrementer, pos._2 - 1)
      }
    }
    possibleAttacks
  }



  override def updateMoved(): ChessPiece = {
    this.copy(hasMoved = true)
  }

  override def toString: String ={
    if (color) {
      return "\u2659"
    }
    "\u265F"
  }

}
