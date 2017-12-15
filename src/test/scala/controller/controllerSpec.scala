package controller;

import model._
import org.specs2.mutable._


class controllerSpec extends Specification{

  "A Controller" should{
    val expected = new ChessController
    val controller = new ChessController
    controller.chessBoard =  new ChessBoardFactory().create(3)
    controller.chessBoard(0)(0) = new King(false)
    controller.chessBoard(0)(2) = new Queen(true)

    expected.chessBoard =  new ChessBoardFactory().create(3)
    expected.chessBoard(0)(0) = new King(false)
    expected.chessBoard(0)(2) = new Queen(true)

    controller.move(2,0,1,2)
    "have no target koordinates" in {
      controller.chessBoard must be_==(expected.chessBoard)
    }
  }

  "A Controller" should{
    val expected = new ChessController
    val controller = new ChessController
    controller.chessBoard =  new ChessBoardFactory().create(2)
    controller.chessBoard(0)(0) = new King(false)
    controller.chessBoard(0)(1) = new Queen(true)

    expected.chessBoard =  new ChessBoardFactory().create(2)
    expected.chessBoard(0)(0) = new King(false)
    expected.chessBoard(0)(1) = new Queen(true)

    controller.move(1,1,0,0)
    "have no source koordinates" in {
      controller.chessBoard must be_==(expected.chessBoard)
    }
  }
  "A Controller" should{
    val startChessboard = new ChessController
    val controller = new ChessController
    controller.chessBoard =  new ChessBoardFactory().create(2)
    controller.chessBoard(0)(0) = new King(false)
    controller.chessBoard(0)(1) = new Queen(true)
    controller.move(1,0,0,0)

    "reset chessboard after check mate" in {
      controller.chessBoard must be_==(startChessboard.chessBoard)
    }
  }

  "A Controller" should{
    val controller = new ChessController
    controller.changePlayer

    "change player" in {
      controller.currentPlayer must be_==(false)
    }
  }

  "A Controller" should{
    val controller = new ChessController

    "have initialized player color" in {
      controller.currentPlayer must be_==(true)
    }
  }


  "A Controller" should{
    val controller = new ChessController

    var chessBoard = new ChessBoardFactory().create(8)
    chessBoard(0)(0) = new Rook(true)

    "have Rook on chessboard" in {
      controller.chessBoard(0)(0) must be_==(chessBoard(0)(0))
    }
  }


  "A Controller" should{
    val controller = new ChessController

    var chessBoard = new ChessBoardFactory().create(8)
    chessBoard(0)(0) = new Rook(true)
    chessBoard(0)(1) = new Knight(true)
    chessBoard(0)(2) = new Bishop(true)
    chessBoard(0)(3) = new Queen(true)
    chessBoard(0)(4) = new King(true)
    chessBoard(0)(5) = new Bishop(true)
    chessBoard(0)(6) = new Knight(true)
    chessBoard(0)(7) = new Rook(true)
    for(i <- 0 to 7){
      chessBoard(1)(i) = new Pawn(true)
    }

    chessBoard(7)(0) = new Rook(false)
    chessBoard(7)(1) = new Knight(false)
    chessBoard(7)(2) = new Bishop(false)
    chessBoard(7)(3) = new Queen(false)
    chessBoard(7)(4) = new King(false)
    chessBoard(7)(5) = new Bishop(false)
    chessBoard(7)(6) = new Knight(false)
    chessBoard(7)(7) = new Rook(false)
    for(i <- 0 to 7){
      chessBoard(6)(i) = new Pawn(false)
    }
    "have initialized chessboard" in {
      controller.chessBoard must be_==(chessBoard)
    }
  }

}