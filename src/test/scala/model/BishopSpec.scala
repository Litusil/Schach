package model

import org.specs2.mutable._

import scala.collection.immutable.Vector


class BishopSpec extends Specification{


  "A Bishop" should{
    val bishop = Bishop(color = true,hasMoved = false)
    val chessBoard = ChessBoard(Vector.fill(3,3)(None: Option[ChessPiece]))

    "not be on chessboard" in {
      bishop.getPosition(chessBoard.field) must be_== ((-1,-1))
    }
  }

  "A Bishop" should{
    val bishop = Option(Bishop(color = true,hasMoved = false))
    var chessBoard = ChessBoard(Vector.fill(3,3)(None: Option[ChessPiece]))

    chessBoard = chessBoard.putPiece(1,1,bishop)
    chessBoard = chessBoard.putPiece(0,0,Option(Bishop(color = true,hasMoved = false)) )
    chessBoard = chessBoard.putPiece(2,2,Option(Bishop(color = false,hasMoved = false)) )

    var moveableFields: Vector[(Int,Int)] = Vector()
    moveableFields = moveableFields :+ (2,0)
    moveableFields = moveableFields :+ (2,2)
    moveableFields = moveableFields :+ (0,2)
    "have possible moves on chessboard" in {
      bishop.get.getPossibleMoves(chessBoard.field) must be_== (moveableFields)
    }
  }

  "A white model.Bishop" should {
    val r = Bishop(color = true,hasMoved = false)
    "have toString() that is \u2657" in {
      r.toString must be_== ("\u2657")
    }
  }

  "A black model.Bishop" should {
    val r = Bishop(color = false,hasMoved = false)
    "have toString that is \u265D" in {
      r.toString must be_== ("\u265D")
    }
  }

  "A black model.Bishop" should {
    var piece: ChessPiece = Bishop(color = false, hasMoved = false)
    piece = piece.updateMoved()
    "update hasMoved" in {
      piece must be_==(Bishop(color = false, hasMoved = true))
    }
  }

}