package model

import org.specs2.mutable._

import scala.collection.immutable.Vector

class KingSpec extends Specification{

  "A King" should{
    val king = Option(King(color = true,hasMoved = false))
    var chessBoard = ChessBoard(Vector.fill(2,2)(None: Option[ChessPiece]))
    var moveableFields: Vector[(Int,Int)] = Vector()

    chessBoard = chessBoard.putPiece(0,0,king)
    chessBoard = chessBoard.putPiece(1,0,Option(King(color = false,hasMoved = false)) )
    chessBoard = chessBoard.putPiece(0,1,Option(King(color = true,hasMoved = false)) )

    moveableFields = moveableFields  :+ (0,1)
    "have possible moves on chessboard" in {
      king.get.getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }

  "A King" should{
    val king = Option(King(color = true,hasMoved = false))
    var chessBoard = ChessBoard(Vector.fill(3,3)(None: Option[ChessPiece]))
    var moveableFields: Vector[(Int,Int)] = Vector()

    chessBoard = chessBoard.putPiece(1,1,king)

    moveableFields = moveableFields :+ (0,1)
    moveableFields = moveableFields  :+ (2,1)
    moveableFields = moveableFields  :+ (1,2)
    moveableFields = moveableFields  :+ (1,0)
    moveableFields = moveableFields  :+ (0,2)
    moveableFields = moveableFields  :+ (2,2)
    moveableFields = moveableFields  :+ (0,0)
    moveableFields = moveableFields  :+ (2,0)
    "have possible moves on chessboard" in {
      king.get.getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }

  "A white model.King" should {
    val r = King(color = true,hasMoved = false)
    "have toString() that is \u2654" in {
      r.toString must be_== ("\u2654")
    }
  }

  "A black model.King" should {
    val r = King(color = false,hasMoved = false)
    "have toString that is \u265A" in {
      r.toString must be_== ("\u265A")
    }
  }

  "A black model.King" should {
    var piece: ChessPiece = King(color = false, hasMoved = false)
    piece = piece.updateMoved()
    "update hasMoved" in {
      piece must be_==(King(color = false, hasMoved = true))
    }
  }
}