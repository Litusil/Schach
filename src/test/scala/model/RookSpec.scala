package model

import org.specs2.mutable._

import scala.collection.immutable.Vector

class RookSpec extends Specification{

  "A Rook" should{
    val rook = Rook(color = true,hasMoved = false)
    val chessBoard = ChessBoard(Vector.fill(3,3)(None: Option[ChessPiece]))

    "not be on chessboard" in {
      rook.getPosition(chessBoard.field) must be_== ((-1,-1))
    }
  }

  "A Rook" should{
    val rook = Option(Rook(color = true,hasMoved = false))
    var chessBoard = ChessBoard(Vector.fill(3,3)(None: Option[ChessPiece]))

    chessBoard = chessBoard.putPiece(1,1,rook)
    chessBoard = chessBoard.putPiece(1,0,Option(Rook(color = true,hasMoved = false)) )
    chessBoard = chessBoard.putPiece(0,1,Option(Rook(color = false,hasMoved = false)) )

    var moveableFields: Vector[(Int,Int)] = Vector()
    moveableFields = moveableFields :+ (1,0)
    moveableFields = moveableFields  :+ (1,2)
    moveableFields = moveableFields  :+ (2,1)
    "have possible moves on chessboard" in {
      rook.get.getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }


  "A white model.Rook" should {
    val r = Rook(color = true,hasMoved = false)
    "have toString() that is \u2656" in {
      r.toString must be_== ("\u2656")
    }
  }


  "A black model.Rook" should {
    var piece:ChessPiece = new Rook(color = false,hasMoved = false)
    piece =  piece.updateMoved()
    "update hasMoved" in {
      piece must be_== (Rook(color = false,hasMoved = true))
    }
  }



}