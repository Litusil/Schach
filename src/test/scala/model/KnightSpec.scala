package model

import org.specs2.mutable._

class KnightSpec extends Specification{

  "A Knight" should{
    var knight = new Knight(true,false)
    var chessBoard = new ChessBoardFactory().create(3)
    var moveableFields: Vector[(Int,Int)] = Vector()
    chessBoard (0)(0) = knight
    chessBoard (1)(2) = new Knight(false,false)
    chessBoard (2)(1) = new Knight(true,false)
    moveableFields = moveableFields :+ (1,2)
    "have possible moves on chessboard" in {
      knight.getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }

  "A Knight" should{
    var knight = new Knight(true,false)
    var chessBoard = new ChessBoardFactory().create(5)
    var moveableFields: Vector[(Int,Int)] = Vector()
    chessBoard (2)(2) = knight
    moveableFields = moveableFields :+ (1,4)
    moveableFields = moveableFields  :+ (3,4)
    moveableFields = moveableFields  :+  (0,3)
    moveableFields = moveableFields  :+ (4,3)
    moveableFields = moveableFields  :+ (0,1)
    moveableFields = moveableFields  :+ (4,1)
    moveableFields = moveableFields  :+ (1,0)
    moveableFields = moveableFields  :+ (3,0)
    "have possible moves on chessboard" in {
      knight .getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }




  "A white model Knight" should {
    val r = new Knight(true,false)
    "have toString() that is \u2658" in {
      r.toString must be_== ("\u2658")
    }


  }

  "A black model Knight" should {
    val r = new Knight(false,false)
    "have toString that is \u265E" in {
      r.toString must be_== ("\u265E")
    }
  }
}