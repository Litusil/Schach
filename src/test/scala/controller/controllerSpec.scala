package controller;

import model._
import org.specs2.mutable._


class controllerSpec extends Specification{

  "A Controller" should{
    val expected = new ChessController
    val controller = new ChessController
    controller.chessBoard =  new ChessBoardFactory().create(3)
    controller.chessBoard(0)(0) = new King(false,false)
    controller.chessBoard(0)(2) = new Queen(true,false)

    expected.chessBoard =  new ChessBoardFactory().create(3)
    expected.chessBoard(0)(0) = new King(false,false)
    expected.chessBoard(0)(2) = new Queen(true,false)

    controller.move(2,0,1,2)
    "have no target koordinates" in {
      controller.chessBoard must be_==(expected.chessBoard)
    }
  }

  "A Controller" should{
    val expected = new ChessController
    val controller = new ChessController
    controller.chessBoard =  new ChessBoardFactory().create(2)
    controller.chessBoard(0)(0) = new King(false,false)
    controller.chessBoard(0)(1) = new Queen(true,false)

    expected.chessBoard =  new ChessBoardFactory().create(2)
    expected.chessBoard(0)(0) = new King(false,false)
    expected.chessBoard(0)(1) = new Queen(true,false)

    controller.move(1,1,0,0)
    "have no source koordinates" in {
      controller.chessBoard must be_==(expected.chessBoard)
    }
  }
  "A Controller" should{
    val startChessboard = new ChessController
    val controller = new ChessController
    controller.chessBoard =  new ChessBoardFactory().create(2)
    controller.chessBoard(0)(0) = new King(false,false)
    controller.chessBoard(0)(1) = new Queen(true,false)
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
    chessBoard(0)(0) = new Rook(true,false)

    "have Rook on chessboard" in {
      controller.chessBoard(0)(0) must be_==(chessBoard(0)(0))
    }
  }


  "A Controller" should{
    val controller = new ChessController

    var chessBoard = new ChessBoardFactory().create(8)
    chessBoard(0)(0) = new Rook(true,false)
    chessBoard(0)(1) = new Knight(true,false)
    chessBoard(0)(2) = new Bishop(true,false)
    chessBoard(0)(3) = new Queen(true,false)
    chessBoard(0)(4) = new King(true,false)
    chessBoard(0)(5) = new Bishop(true,false)
    chessBoard(0)(6) = new Knight(true,false)
    chessBoard(0)(7) = new Rook(true,false)
    for(i <- 0 to 7){
      chessBoard(1)(i) = new Pawn(true,false)
    }

    chessBoard(7)(0) = new Rook(false,false)
    chessBoard(7)(1) = new Knight(false,false)
    chessBoard(7)(2) = new Bishop(false,false)
    chessBoard(7)(3) = new Queen(false,false)
    chessBoard(7)(4) = new King(false,false)
    chessBoard(7)(5) = new Bishop(false,false)
    chessBoard(7)(6) = new Knight(false,false)
    chessBoard(7)(7) = new Rook(false,false)
    for(i <- 0 to 7){
      chessBoard(6)(i) = new Pawn(false,false)
    }
    "have initialized chessboard" in {
      controller.chessBoard must be_==(chessBoard)
    }
  }

}