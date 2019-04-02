package model

import org.specs2.mutable._

class QueenSpec extends Specification{


  "A Queen" should{
    val queen = Queen(color = true,moved = false)
    val chessBoard = new ChessBoardFactory().create(3)
    "not be on chessboard" in {
      queen.getPosition(chessBoard) must be_== ((-1,-1))
    }
  }

  "A Queen" should{
    val queen = Option(Queen(color = true,moved = false))
    val chessBoard = new ChessBoardFactory().create(2)
    chessBoard (1)(1) = queen
    chessBoard (0)(1) = Option(Queen(color = true,moved = false))
    chessBoard (1)(0) = Option(Queen(color = true,moved = false))
    chessBoard (0)(0) = Option(Queen(color = true,moved = false))
    val moveableFields: Vector[(Int,Int)] = Vector()
    "have no possible moves on chessboard" in {
      queen.get.getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }

  "A Queen" should{
    val queen = Option(Queen(color = true,moved = false))
    val chessBoard = new ChessBoardFactory().create(3)
    chessBoard (1)(1) = queen
    chessBoard (0)(1) = Option(Queen(color = true,moved = false))
    chessBoard (1)(0) = Option(Queen(color = false,moved = false))
    var moveableFields: Vector[(Int,Int)] = Vector()
    moveableFields = moveableFields :+ (1,0)
    moveableFields = moveableFields :+ (1,2)
    moveableFields = moveableFields  :+ (2,1)
    moveableFields = moveableFields  :+ (2,0)
    moveableFields = moveableFields  :+ (2,2)
    moveableFields = moveableFields  :+ (0,0)
    moveableFields = moveableFields  :+ (0,2)
    "have possible moves on chessboard" in {
      queen.get.getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }


  "A white model.Queen" should {
    val r = Queen(color = true,moved = false)
    "have toString() that is \u2655" in {
      r.toString must be_== ("\u2655")
    }


  }

  "A black model.Queen" should {
    val r = Queen(color = false,moved = false)
    "have toString that is \u265C" in {
      r.toString must be_== ("\u265B")
    }
  }



}

