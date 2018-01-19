package model

import org.specs2.mutable._


class BishopSpec extends Specification{


  "A Bishop" should{
    var r = new Bishop(true,false)
    var chessBoard = new ChessBoardFactory().create(3)
    "not be on chessboard" in {
      r.getPosition(chessBoard) must be_== ((-1,-1))
    }
  }

  "A Bishop" should{
    var r = new Bishop(true,false)
    var chessBoard = new ChessBoardFactory().create(3)
    chessBoard (1)(1) = r
    chessBoard (0)(0) = new Bishop(true,false)
    chessBoard (2)(2) = new Bishop(false,false)
    var moveableFields: Vector[(Int,Int)] = Vector()
    moveableFields = moveableFields :+ (2,0)
    moveableFields = moveableFields :+ (2,2)
    moveableFields = moveableFields :+ (0,2)
    "have possible moves on chessboard" in {
      r.getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }

  "A white model.Bishop" should {
    val r = new Bishop(true,false)
    "have toString() that is \u2657" in {
      r.toString must be_== ("\u2657")
    }
  }

  "A black model.Bishop" should {
    val r = new Bishop(false,false)
    "have toString that is \u265D" in {
      r.toString must be_== ("\u265D")
    }
  }
}