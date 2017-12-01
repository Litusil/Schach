package model

import org.specs2.mutable._

class PawnSpec extends Specification{

  "A Pawn" should{
    var pawn = new Pawn(true)
    var chessBoard = new ChessBoardFactory().create(3)
    "not be on chessboard" in {
      pawn.getPosition(chessBoard) must be_== ((-1,-1))
    }
  }

  "A Pawn" should{
    var pawn = new Pawn(true)
    var chessBoard = new ChessBoardFactory().create(3)
    var moveableFields: Vector[(Int,Int)] = Vector()
    chessBoard (2)(1) = pawn

    "have no possible moves on chessboard" in {
      pawn.getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }

  "A Pawn" should{
    var pawn = new Pawn(true)
    var chessBoard = new ChessBoardFactory().create(3)
    var moveableFields: Vector[(Int,Int)] = Vector()
    chessBoard (0)(1) = pawn
    chessBoard (1)(0) = new Pawn(false)
    chessBoard (1)(2) = new Pawn(true)

    moveableFields :+ (1,1)
    moveableFields :+ (2,1)
    moveableFields :+ (1,0)
    "have possible moves on chessboard" in {
      pawn.getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }


  "A white model.Pawn" should {
    val r = new Pawn(true)
    "have toString() that is \u2659" in {
      r.toString must be_== ("\u2659")
    }


  }

  "A black model.Pawn" should {
    val r = new Pawn(false)
    "have toString that is \u265F" in {
      r.toString must be_== ("\u265F")
    }
  }
}

