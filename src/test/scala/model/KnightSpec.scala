package model

import org.specs2.mutable._

import scala.collection.immutable.Vector

class KnightSpec extends Specification{

  "A Knight" should{
    val knight = Option(Knight(color = true,hasMoved = false))
    var chessBoard = ChessBoard(Vector.fill(3,3)(None: Option[ChessPiece]))
    var moveableFields: Vector[(Int,Int)] = Vector()

    chessBoard = chessBoard.putPiece(0,0,knight)
    chessBoard = chessBoard.putPiece(2,1,Option(Knight(color = false,hasMoved = false)) )
    chessBoard = chessBoard.putPiece(1,2,Option(Knight(color = true,hasMoved = false)) )

    moveableFields = moveableFields :+ (1,2)
    "have possible moves on chessboard" in {
      knight.get.getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }

  "A Knight" should{
    val knight = Option(Knight(color = true,hasMoved = false))
    var chessBoard = ChessBoard(Vector.fill(5,5)(None: Option[ChessPiece]))
    var moveableFields: Vector[(Int,Int)] = Vector()
    chessBoard = chessBoard.putPiece(2,2,knight)
    moveableFields = moveableFields  :+ (4,1)
    moveableFields = moveableFields  :+ (4,3)
    moveableFields = moveableFields  :+ (3,0)
    moveableFields = moveableFields  :+ (3,4)
    moveableFields = moveableFields  :+ (1,0)
    moveableFields = moveableFields  :+ (1,4)
    moveableFields = moveableFields  :+ (0,1)
    moveableFields = moveableFields  :+ (0,3)
    "have possible moves on chessboard" in {
      knight.get.getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }

  "A white model Knight" should {
    val r = Knight(color = true,hasMoved = false)
    "have toString() that is \u2658" in {
      r.toString must be_== ("\u2658")
    }
  }

  "A black model Knight" should {
    val r = Knight(color = false,hasMoved = false)
    "have toString that is \u265E" in {
      r.toString must be_== ("\u265E")
    }
  }

  "A black model.Knight" should {
    var piece: ChessPiece = Knight(color = false, hasMoved = false)
    piece = piece.updateMoved()
    "update hasMoved" in {
      piece must be_==(Knight(color = false, hasMoved = true))
    }
  }
}