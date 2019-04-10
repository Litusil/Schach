package model

import controller.ChessController
import org.specs2.mutable._

import scala.collection.immutable.Vector


class ChessBoardSpec extends Specification{


  "A Chessboard" should{
    val chesscontroller = new ChessController
    val pieceFactory = new ChessPieceFactory
    var chessBoard = ChessBoard(Vector.fill(2,2)(None: Option[ChessPiece]))
    chesscontroller.newGame()

    chessBoard = chessBoard.putPiece(0,0,pieceFactory.create("♚", true))
    chessBoard = chessBoard.putPiece(1,1,pieceFactory.create("♕", true))

    chessBoard = chessBoard.move(1,1,0,0).get

    "equal defaultinit" in {
      chesscontroller.chessBoard must be_== (chessBoard)
    }
  }

  "A Chessboard" should{
    val chesscontroller = new ChessController
    val pieceFactory = new ChessPieceFactory
    var chessBoard = ChessBoard(Vector.fill(2,2)(None: Option[ChessPiece]))
    chesscontroller.newGame()
    chessBoard = chessBoard.changePlayer()

    chessBoard = chessBoard.putPiece(0,0,pieceFactory.create("♔", true))
    chessBoard = chessBoard.putPiece(1,1,pieceFactory.create("♛", true))

    chessBoard = chessBoard.move(1,1,0,0).get

    "equal defaultinit" in {
      chesscontroller.chessBoard must be_== (chessBoard)
    }
  }


  "A Chessboard" should{
    val chesscontroller = new ChessController
    val pieceFactory = new ChessPieceFactory
    var chessBoard = ChessBoard(Vector())


    "equal defaultinit" in {
      chessBoard.move(0,0,1,1) must be_== (None)
    }
  }



}