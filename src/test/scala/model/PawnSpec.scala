package model

import org.specs2.mutable._

class PawnSpec extends Specification{


  "A Pawn" should{
    val pawn = Pawn(color = true,moved = false)
    val chessBoard = new ChessBoardFactory().create(3)
    val moveableFields: Vector[(Int,Int)] = Vector()
    chessBoard (0)(1) = pawn
    chessBoard(1)(1) = Pawn(color = false,moved = false)
    "have no possible moves on chessboard" in {
      pawn.getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }

  "A Pawn" should{
    val pawn = Pawn(color = false,moved = false)
    val chessBoard = new ChessBoardFactory().create(3)
    val moveableFields: Vector[(Int,Int)] = Vector()
    chessBoard (2)(1) = pawn
    chessBoard(1)(1) = Pawn(color = false,moved = false)
    "have no possible moves on chessboard" in {
      pawn.getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }

  "A Pawn" should{
    val pawn = Pawn(color = false,moved = false)
    val chessBoard = new ChessBoardFactory().create(3)
    var moveableFields: Vector[(Int,Int)] = Vector()
    chessBoard (2)(1) = pawn
    chessBoard(1)(0) = Pawn(color = true,moved = false)
    chessBoard(1)(2) = Pawn(color = true,moved = false)
    moveableFields = moveableFields :+ (1, 1)
    moveableFields = moveableFields :+ (1, 2)
    moveableFields = moveableFields :+ (1, 0)
    moveableFields = moveableFields :+ (0, 1)
    "have possible moves on chessboard" in {
      pawn.getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }

  "A Pawn" should{
    val pawn = Pawn(color = true,moved = false)
    val chessBoard = new ChessBoardFactory().create(3)
    "not be on chessboard" in {
      pawn.getPosition(chessBoard) must be_== ((-1,-1))
    }
  }

  "A Pawn" should{
    val pawn = Pawn(color = true,moved = false)
    val chessBoard = new ChessBoardFactory().create(3)
    val moveableFields: Vector[(Int,Int)] = Vector()
    chessBoard (2)(1) = pawn

    "have no possible moves on chessboard" in {
      pawn.getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }

  "A Pawn" should{
    val pawn = Pawn(color = true,moved = false)
    val chessBoard = new ChessBoardFactory().create(3)
    var moveableFields: Vector[(Int,Int)] = Vector()
    chessBoard (0)(1) = pawn
    chessBoard (1)(0) = Pawn(color = false,moved = false)
    chessBoard (1)(2) = Pawn(color = true,moved = false)

    moveableFields = moveableFields :+ (1,1)
    moveableFields = moveableFields :+ (1,0)
    moveableFields = moveableFields :+ (2,1)
    "have possible moves on chessboard" in {
      pawn.getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }


  "A white model.Pawn" should {
    val r = Pawn(color = true,moved = false)
    "have toString() that is \u2659" in {
      r.toString must be_== ("\u2659")
    }


  }

  "A black model.Pawn" should {
    val r = Pawn(color = false,moved = false)
    "have toString that is \u265F" in {
      r.toString must be_== ("\u265F")
    }
  }
}

