package model

import org.specs2.mutable._

class KingSpec extends Specification{

  "A King" should{
    var king = new King(true)
    var chessBoard = new ChessBoardFactory().create(2)
    var moveableFields: Vector[(Int,Int)] = Vector()
    chessBoard (0)(0) = king
    chessBoard (0)(1) = new King(false)
    chessBoard (1)(0) = new King(true)
    moveableFields = moveableFields  :+ (0,1)
    moveableFields = moveableFields  :+ (1,1)
    "have possible moves on chessboard" in {
      king.getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }

  "A King" should{
    var king = new King(true)
    var chessBoard = new ChessBoardFactory().create(3)
    var moveableFields: Vector[(Int,Int)] = Vector()
    chessBoard (1)(1) = king
    moveableFields = moveableFields :+ (1,0)
    moveableFields = moveableFields  :+ (1,2)
    moveableFields = moveableFields  :+ (2,1)
    moveableFields = moveableFields  :+ (0,1)
    moveableFields = moveableFields  :+ (2,0)
    moveableFields = moveableFields  :+ (2,2)
    moveableFields = moveableFields  :+ (0,0)
    moveableFields = moveableFields  :+ (0,2)
    "have possible moves on chessboard" in {
      king.getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }





  "A white model.King" should {
    val r = new King(true)
    "have toString() that is \u2654" in {
      r.toString must be_== ("\u2654")
    }


  }

  "A black model.King" should {
    val r = new King(false)
    "have toString that is \u265A" in {
      r.toString must be_== ("\u265A")
    }

  }
}