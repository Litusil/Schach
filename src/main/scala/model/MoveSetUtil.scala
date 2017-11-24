package model

import scala.collection.immutable.Vector
import scala.collection.mutable.Map
import scala.util.control.Breaks.{break, breakable}

object Direction extends Enumeration {
  val LEFT = Value
  val RIGHT = Value
  val UP = Value
  val DOWN = Value
}

object MoveSetUtil {
  var moves = Map(Direction.LEFT -> (-1,0), Direction.RIGHT -> (1,0), Direction.UP -> (0,1), Direction.DOWN -> (0,-1))


  def laneMovement(xKoordinate:Integer,yKoordinate:Integer, direction: Direction.Value,
  chessBoard:Array[Array[ChessPiece]]): Vector[(Int, Int)] ={
    var selectableFields: Vector[(Int,Int)] = Vector()
    var x = xKoordinate
    var y = yKoordinate
    breakable {
      while (x + moves(direction)._1 >= 0 && x + moves(direction)._1 < chessBoard.length
        && y + moves(direction)._2 >= 0 && y + moves(direction)._2 < chessBoard.length) {
        x += moves(direction)._1
        y += moves(direction)._2

        if (chessBoard(x)(y) != null) {
          if (chessBoard(x)(y).color != chessBoard(xKoordinate)(yKoordinate).color) {
            selectableFields :+ (x, y)
          }
          break
        }
        selectableFields :+ (x, y)
      }
    }
    selectableFields
  }

  def diagonalMovement (): Unit = {

  }


}
