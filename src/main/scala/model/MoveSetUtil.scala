package model

import scala.collection.immutable.Vector
import scala.collection.mutable.Map
import scala.util.control.Breaks.{break, breakable}

object Direction extends Enumeration {
  val LEFT = Value
  val RIGHT = Value
  val UP = Value
  val DOWN = Value
  val LEFT_UP = Value
  val RIGHT_UP = Value
  val LEFT_DOWN = Value
  val RIGHT_DOWN = Value
}

object MoveSetUtil {
  var moves = Map[Direction.Value, (Int, Int)]()
  moves += Direction.LEFT -> (-1,0)
  moves += Direction.RIGHT -> (1,0)
  moves += Direction.UP -> (0,1)
  moves += Direction.DOWN -> (0,-1)

  moves += Direction.LEFT_UP -> (-1,1)
  moves += Direction.RIGHT_UP -> (1,1)
  moves += Direction.LEFT_DOWN -> (-1,-1)
  moves += Direction.RIGHT_DOWN -> (1,-1)



  def getSelectableFields(xKoordinate:Int,yKoordinate:Int, direction: Direction.Value,
                   chessBoard:Array[Array[ChessPiece]]): Vector[(Int, Int)] ={

    var selectableFields: Vector[(Int,Int)] = Vector()
    var x = xKoordinate
    var y = yKoordinate
    breakable {
      while (x + moves(direction)._1 >= 0 && x + moves(direction)._1 < chessBoard.length
        && y + moves(direction)._2 >= 0 && y + moves(direction)._2 < chessBoard.length) {
        x += moves(direction)._1
        y += moves(direction)._2


        if (chessBoard(y)(x) == null ) {
          selectableFields = selectableFields :+ (y, x)
        }else {
          if (chessBoard(y)(x).color != chessBoard(yKoordinate)(xKoordinate).color) {
            selectableFields = selectableFields :+ (y, x)
          }
          break
        }

      }
    }
    return selectableFields
  }

}
