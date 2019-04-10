package model

import org.specs2.mutable._

import scala.collection.immutable.Vector

class QueenSpec extends Specification{


  "A Queen" should{
    val queen = Queen(color = true,hasMoved = false)
    val chessBoard = ChessBoard(Vector.fill(3,3)(None: Option[ChessPiece]))
    "not be on chessboard" in {
      queen.getPosition(chessBoard.field) must be_== ((-1,-1))
    }
  }

  "A Queen" should{
    val queen = Option(Queen(color = true,hasMoved = false))
    var chessBoard = ChessBoard(Vector.fill(2,2)(None: Option[ChessPiece]))

    chessBoard = chessBoard.putPiece(1,1,queen)
    chessBoard = chessBoard.putPiece(0,1,Option(Queen(color = true,hasMoved = false)))
    chessBoard = chessBoard.putPiece(1,0,Option(Queen(color = true,hasMoved = false)))
    chessBoard = chessBoard.putPiece(0,0,Option(Queen(color = true,hasMoved = false)))

    val moveableFields: Vector[(Int,Int)] = Vector()
    "have no possible moves on chessboard" in {
      queen.get.getPossibleMoves(chessBoard.field) must be_== (moveableFields)
    }
  }

  "A Queen" should{
    val queen = Option(Queen(color = true,hasMoved = false))
    var chessBoard = ChessBoard(Vector.fill(3,3)(None: Option[ChessPiece]))

    chessBoard = chessBoard.putPiece(1,1,queen)
    chessBoard = chessBoard.putPiece(1,0,Option(Queen(color = true,hasMoved = false)))
    chessBoard = chessBoard.putPiece(0,1,Option(Queen(color = false,hasMoved = false)))

    var moveableFields: Vector[(Int,Int)] = Vector()
    moveableFields = moveableFields :+ (1,0)
    moveableFields = moveableFields :+ (1,2)
    moveableFields = moveableFields  :+ (2,1)
    moveableFields = moveableFields  :+ (2,0)
    moveableFields = moveableFields  :+ (2,2)
    moveableFields = moveableFields  :+ (0,0)
    moveableFields = moveableFields  :+ (0,2)
    "have possible moves on chessboard" in {
      queen.get.getPossibleMoves(chessBoard.field) must be_== (moveableFields)
    }
  }

  "A white model.Queen" should {
    val r = Queen(color = true,hasMoved = false)
    "have toString() that is \u2655" in {
      r.toString must be_== ("\u2655")
    }
  }

  "A black model.Queen" should {
    val r = Queen(color = false,hasMoved = false)
    "have toString that is \u265C" in {
      r.toString must be_== ("\u265B")
    }
  }

  "A black model.Queen" should {
    var piece:ChessPiece = Queen(color = false,hasMoved = false)
    piece =  piece.updateMoved()
    "update hasMoved" in {
      piece must be_== (Queen(color = false,hasMoved = true))
    }
  }



}

