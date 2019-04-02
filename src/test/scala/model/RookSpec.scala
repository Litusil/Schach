package model

import org.specs2.mutable._

import scala.collection.immutable.Vector

class RookSpec extends Specification{

  "A Rook" should{
    val r = Rook(color = true,moved = false)
    val chessBoard = new ChessBoardFactory().create(3)
    "not be on chessboard" in {
      r.getPosition(chessBoard) must be_== ((-1,-1))
    }
  }

  "A Rook" should{
    val r = Rook(color = true,moved = false)
    val chessBoard = new ChessBoardFactory().create(3)
    chessBoard (1)(1) = r
    chessBoard (0)(1) = Rook(color = true,moved = false)
    chessBoard (1)(0) = Rook(color = false,moved = false)
    var moveableFields: Vector[(Int,Int)] = Vector()
    moveableFields = moveableFields :+ (1,0)
    moveableFields = moveableFields  :+ (1,2)
    moveableFields = moveableFields  :+ (2,1)
    "have possible moves on chessboard" in {
      r.getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }


  "A white model.Rook" should {
    val r = Rook(color = true,moved = false)
    "have toString() that is \u2656" in {
      r.toString must be_== ("\u2656")
    }
  }

  "A black model.Rook" should {
    val r = Rook(color = false,moved = false)
    "have toString that is \u265C" in {
      r.toString must be_== ("\u265C")
    }
  }



}