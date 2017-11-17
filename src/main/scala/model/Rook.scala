package model

import scala.util.control.Breaks._
import scala.collection.immutable.Vector

class Rook(color: Boolean) extends ChessPiece {

  override def getPossibleMoves(chessBoard: Array[Array[ChessPiece]]): Array[(Int, Int)] = {
    var pos = this.getPosition(chessBoard);
    var possibleMoves: Vector[(Int,Int)] = Vector();
    var i = 1
    while(chessBoard(pos._1)(pos._2 - i) != null && pos._2 - i >= 0){
      possibleMoves :+ (pos._1, pos._2 - i)
    }
    //TODO rest of implementation!!!


    var x = 0
    var y = 0



    return Array ((x,y))
  }

  override def toString(): String = {
    if (color) {
      return "\u2656"
    }
    "\u265C"
  }

}
