package model

import org.specs2.mutable._

import scala.collection.immutable.Vector


class ChessBoardSpec extends Specification{

  "A Chessboard" should{
    val chessBoard = ChessBoard(Vector.fill(8,8)(None: Option[ChessPiece])).defaultInit()


    "have a default init" in {
      chessBoard.currentPlayer must be_== (true)
    }
  }

  "A Chessboard" should{
    val pieceFactory = new ChessPieceFactory
    var chessBoard = ChessBoard(Vector.fill(2,2)(None: Option[ChessPiece]))

    chessBoard = chessBoard.putPiece(0,0,pieceFactory.create("♚", true))
    chessBoard = chessBoard.putPiece(1,1,pieceFactory.create("♕", true))

    chessBoard = chessBoard.move(1,1,0,0).get

    "have black checkmate" in {
      chessBoard.checkmate.get must be_== (false)
    }
  }

  "A Chessboard" should{
    val pieceFactory = new ChessPieceFactory
    var chessBoard = ChessBoard(Vector.fill(2,2)(None: Option[ChessPiece]))
    chessBoard = chessBoard.changePlayer()

    chessBoard = chessBoard.putPiece(0,0,pieceFactory.create("♔", true))
    chessBoard = chessBoard.putPiece(1,1,pieceFactory.create("♛", true))

    chessBoard = chessBoard.move(1,1,0,0).get
    "have white checkmate" in {
      chessBoard.checkmate.get must be_== (true)
    }
  }


  "A empty Chessboard" should{
    val chessBoard = ChessBoard(Vector())


    "have no valid move" in {
      chessBoard.move(0,0,1,1) must be_== (None)
    }
  }

  "A Chessboard" should{

    var chessBoard = ChessBoard(Vector.fill(2,2)(None: Option[ChessPiece]))
    val pieceFactory = new ChessPieceFactory

    chessBoard = chessBoard.putPiece(0,0,pieceFactory.create("♔", true))

    "have no valid move" in {
      chessBoard.move(0,0,3,1) must be_== (None)
    }
  }

  "A Chessboard" should{
    val chessBoard = ChessBoard(Vector.fill(8,8)(None: Option[ChessPiece])).defaultInit()
    val jsonChessboard = chessBoard.toJson()

    "be a JSON" in {
      jsonChessboard.toString().length must be_>= (0)
    }
  }


}