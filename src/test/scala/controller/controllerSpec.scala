package controller

import model._
import model.fileIOComponent.FileIOInterface
import model.fileIOComponent.fileIoXmlImpl.FileIO
import org.specs2.mutable._


class controllerSpec extends Specification{

  var slmanager:FileIOInterface = new FileIO

  "A Controller" should{
    val expected = new ChessController
    val controller = new ChessController
    controller.chessBoard.board =  new ChessBoardFactory().create(3)
    controller.chessBoard.board(0)(0) = King(color = false,moved = false,(0,0))
    controller.chessBoard.board(0)(2) = Queen(color = true,moved = false,(0,2))

    expected.chessBoard.board =  new ChessBoardFactory().create(3)
    expected.chessBoard.board(0)(0) = King(color = false,moved = false,(0,0))
    expected.chessBoard.board(0)(2) = Queen(color = true,moved = false,(0,2))

    controller.move(2,0,1,2)
    "have no target koordinates" in {
      controller.chessBoard.board must be_==(expected.chessBoard.board)
    }
  }

  "A Controller" should{
    val controller = new ChessController
    val player = false
    val chessBoard = new ChessBoardFactory().create(3)
    chessBoard(1)(1) = Pawn(color = true,moved = true,(1,1))
    controller.currentPlayer = player
    controller.chessBoard.board = chessBoard
    controller.save()
    controller.load()
    "save and load correct" in {
     controller.currentPlayer must be_==(player)
     controller.chessBoard.board must be_==(chessBoard)
    }
  }

  "A Controller" should{
    val expected = new ChessController
    val controller = new ChessController
    controller.chessBoard.board = new ChessBoardFactory().create(2)
    controller.chessBoard.board(0)(0) = King(color = false,moved = false,(0,0))
    controller.chessBoard.board(0)(1) = Queen(color = true,moved = false,(0,1))

    expected.chessBoard.board =  new ChessBoardFactory().create(2)
    expected.chessBoard.board(0)(0) = King(color = false,moved = false,(0,0))
    expected.chessBoard.board(0)(1) = Queen(color = true,moved = false,(0,1))

    controller.move(1,1,0,0)
    "have no source koordinates" in {
      controller.chessBoard.board must be_==(expected.chessBoard)
    }
  }
  "A Controller" should{
    val startChessboard = new ChessController
    val controller = new ChessController
    controller.chessBoard.board =  new ChessBoardFactory().create(2)
    controller.chessBoard.board(0)(0) = King(color = false,moved = false,(0,0))
    controller.chessBoard.board(0)(1) = Queen(color = true,moved = false,(0,1))
    controller.move(1,0,0,0)

    "reset chessboard after white check mate" in {
      controller.chessBoard.board must be_==(startChessboard.chessBoard.board)
    }
  }

  "A Controller" should{
    val startChessboard = new ChessController
    val controller = new ChessController
    controller.currentPlayer = false
    controller.chessBoard.board =  new ChessBoardFactory().create(2)
    controller.chessBoard.board(0)(0) = King(color = true,moved = false,(0,0))
    controller.chessBoard.board(0)(1) = Queen(color = false,moved = false,(0,1))
    controller.move(1,0,0,0)

    "reset chessboard after black check mate" in {
      controller.chessBoard.board must be_==(startChessboard.chessBoard.board)
    }
  }

  "A Controller" should{
    val controller = new ChessController
    controller.changePlayer()

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

    val chessBoard = new ChessBoardFactory().create(8)
    chessBoard(0)(0) = Rook(color = true,moved = false,(0,0))

    "have Rook on chessboard" in {
      controller.chessBoard.board(0)(0) must be_==(chessBoard(0)(0))
    }
  }


  "A Controller" should{
    val controller = new ChessController

    val chessBoard = new ChessBoardFactory().create(8)
    chessBoard(0)(0) = Rook(color = true,moved = false,(0,0))
    chessBoard(0)(1) = Knight(color = true,moved = false,(0,1))
    chessBoard(0)(2) = Bishop(color = true,moved = false,(0,2))
    chessBoard(0)(3) = Queen(color = true,moved = false,(0,3))
    chessBoard(0)(4) = King(color = true,moved = false,(0,4))
    chessBoard(0)(5) = Bishop(color = true,moved = false,(0,5))
    chessBoard(0)(6) = Knight(color = true,moved = false,(0,6))
    chessBoard(0)(7) = Rook(color = true,moved = false,(0,7))
    for(i <- 0 to 7){
      chessBoard(1)(i) = Pawn(color = true,moved = false,(1,i))
    }

    chessBoard(7)(0) = Rook(color = false, moved = false,(7,0))
    chessBoard(7)(1) = Knight(color = false, moved = false,(7,1))
    chessBoard(7)(2) = Bishop(color = false, moved = false,(7,2))
    chessBoard(7)(3) = Queen(color = false, moved = false,(7,3))
    chessBoard(7)(4) = King(color = false, moved = false,(7,4))
    chessBoard(7)(5) = Bishop(color = false, moved = false,(7,5))
    chessBoard(7)(6) = Knight(color = false, moved = false,(7,6))
    chessBoard(7)(7) = Rook(color = false, moved = false,(7,7))
    for(i <- 0 to 7){
      chessBoard(6)(i) = Pawn(color = false, moved = false,(6,i))
    }
    "have initialized chessboard" in {
      controller.chessBoard.board must be_==(chessBoard)
    }
  }

}