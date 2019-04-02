package model

import org.specs2.mutable._


class BishopSpec extends Specification{


  "A Bishop" should{
    val bishop = Bishop(color = true,moved = false)
    val reference = bishop
    "not be on chessboard" in {
      bishop must be_==(reference)
    }
  }

  "A Bishop" should{
    val r = Bishop(color = true,moved = false)
    val chessBoard = new ChessBoardFactory().create(3)
    "not be on chessboard" in {
      r.getPosition(chessBoard) must be_== ((-1,-1))
    }
  }

  "A Bishop" should{
    val r = Option(Bishop(color = true,moved = false))
    val chessBoard = new ChessBoardFactory().create(3)
    chessBoard (1)(1) = r
    chessBoard (0)(0) = Option(Bishop(color = true,moved = false))
    chessBoard (2)(2) = Option(Bishop(color = false,moved = false))
    var moveableFields: Vector[(Int,Int)] = Vector()
    moveableFields = moveableFields :+ (2,0)
    moveableFields = moveableFields :+ (2,2)
    moveableFields = moveableFields :+ (0,2)
    "have possible moves on chessboard" in {
      r.get.getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }

  "A white model.Bishop" should {
    val r = Bishop(color = true,moved = false)
    "have toString() that is \u2657" in {
      r.toString must be_== ("\u2657")
    }
  }

  "A black model.Bishop" should {
    val r = Bishop(color = false,moved = false)
    "have toString that is \u265D" in {
      r.toString must be_== ("\u265D")
    }
  }
}