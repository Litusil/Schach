package model

import org.specs2.mutable._

import scala.collection.immutable.Vector

class PawnSpec extends Specification{


  "A Pawn" should{
    val pawn = Option(Pawn(color = true,hasMoved = false))
    var chessBoard = ChessBoard(Vector.fill(3,3)(None: Option[ChessPiece]))
    val moveableFields: Vector[(Int,Int)] = Vector()

    chessBoard = chessBoard.putPiece(1,0,pawn)
    chessBoard = chessBoard.putPiece(1,1,Option(Rook(color = false,hasMoved = false)))

    "have no possible moves on chessboard" in {
      pawn.get.getPossibleMoves(chessBoard.field) must be_== (moveableFields)
    }
  }

  "A Pawn" should{
    val pawn = Option(Pawn(color = false,hasMoved = false))
    var chessBoard = ChessBoard(Vector.fill(3,3)(None: Option[ChessPiece]))
    val moveableFields: Vector[(Int,Int)] = Vector()

    chessBoard = chessBoard.putPiece(1,2,pawn)
    chessBoard = chessBoard.putPiece(1,1,Option(Rook(color = false,hasMoved = false)))

    "have no possible moves on chessboard" in {
      pawn.get.getPossibleMoves(chessBoard.field) must be_== (moveableFields)
    }
  }

  "A Pawn" should{
    val pawn = Option(Pawn(color = false,hasMoved = false))
    var chessBoard = ChessBoard(Vector.fill(3,3)(None: Option[ChessPiece]))
    var moveableFields: Vector[(Int,Int)] = Vector()

    chessBoard = chessBoard.putPiece(1,2,pawn)
    chessBoard = chessBoard.putPiece(0,1,Option(Pawn(color = true,hasMoved = false)))
    chessBoard = chessBoard.putPiece(2,1,Option(Pawn(color = true,hasMoved = false)))

    moveableFields = moveableFields :+ (1, 1)
    moveableFields = moveableFields :+ (1, 2)
    moveableFields = moveableFields :+ (1, 0)
    moveableFields = moveableFields :+ (0, 1)
    "have possible moves on chessboard" in {
      pawn.get.getPossibleMoves(chessBoard.field) must be_== (moveableFields)
    }
  }

  "A Pawn" should{
    val pawn = Pawn(color = true,hasMoved = false)
    var chessBoard = ChessBoard(Vector.fill(3,3)(None: Option[ChessPiece]))
    "not be on chessboard" in {
      pawn.getPosition(chessBoard.field) must be_== ((-1,-1))
    }
  }

  "A Pawn" should{
    val pawn = Option(Pawn(color = true,hasMoved = false))
    var chessBoard = ChessBoard(Vector.fill(3,3)(None: Option[ChessPiece]))
    val moveableFields: Vector[(Int,Int)] = Vector()

    chessBoard = chessBoard.putPiece(1,2,pawn)

    "have no possible moves on chessboard" in {
      pawn.get.getPossibleMoves(chessBoard.field) must be_== (moveableFields)
    }
  }

  "A Pawn" should{
    val pawn = Option(Pawn(color = true,hasMoved = false))
    var chessBoard = ChessBoard(Vector.fill(3,3)(None: Option[ChessPiece]))
    var moveableFields: Vector[(Int,Int)] = Vector()

    chessBoard = chessBoard.putPiece(1,0,pawn)
    chessBoard = chessBoard.putPiece(0,1,Option(Pawn(color = false,hasMoved = false)))
    chessBoard = chessBoard.putPiece(2,1,Option(Pawn(color = true,hasMoved = false)))

    moveableFields = moveableFields :+ (1,1)
    moveableFields = moveableFields :+ (1,0)
    moveableFields = moveableFields :+ (2,1)
    "have possible moves on chessboard" in {
      pawn.get.getPossibleMoves(chessBoard.field) must be_== (moveableFields)
    }
  }

  "A white model.Pawn" should {
    val r = Pawn(color = true,hasMoved = false)
    "have toString() that is \u2659" in {
      r.toString must be_== ("\u2659")
    }
  }

  "A black model.Pawn" should {
    val r = Pawn(color = false,hasMoved = false)
    "have toString that is \u265F" in {
      r.toString must be_== ("\u265F")
    }
  }

  "A black model.Pawn" should {
    var piece: ChessPiece = Pawn(color = false, hasMoved = false)
    piece = piece.updateMoved()
    "update hasMoved" in {
      piece must be_==(Pawn(color = false, hasMoved = true))
    }
  }

}

