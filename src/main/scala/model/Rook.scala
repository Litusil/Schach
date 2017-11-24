package model

import scala.util.control.Breaks._
import scala.collection.immutable.Vector

class Rook(color: Boolean) extends ChessPiece(color) {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Vector[(Int, Int)] = {
    var pos = this.getPosition(chessBoard)
    var possibleMoves: Vector[(Int,Int)] = Vector()
    possibleMoves :+ moveFunction(pos._2, pos._1, 1, 0 ,chessBoard)
    possibleMoves :+ moveFunction(pos._2, pos._1, 0, 1 ,chessBoard)
    possibleMoves :+ moveFunction(pos._2, pos._1, -1, 0 ,chessBoard)
    possibleMoves :+ moveFunction(pos._2, pos._1, 0, -1 ,chessBoard)
    possibleMoves
  }

  private def moveFunction (xKoordinate:Integer,yKoordinate:Integer, xIncrementer: Integer, yIncrementer: Integer,
                            chessBoard:Array[Array[ChessPiece]]): Vector[(Int, Int)] ={
    var possibleFields: Vector[(Int,Int)] = Vector()
    var x = xKoordinate
    var y = yKoordinate
    breakable {
      while (x + xIncrementer >= 0 && x + xIncrementer < chessBoard.length
        && y + yIncrementer >= 0 && y + yIncrementer < chessBoard.length) {
        x += xIncrementer
        y += yIncrementer

        if (chessBoard(x)(y) != null) {
          if (chessBoard(x)(y).color != this.color) {

          }
          possibleFields :+ (x, y)
          break
        }
        possibleFields :+ (x, y)
      }
    }
    possibleFields
  }



  override def toString(): String = {
    if (color) {
      return "\u2656"
    }
    "\u265C"
  }

}
