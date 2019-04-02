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
    controller.chessBoard =  new ChessBoardFactory().create(3)
    controller.chessBoard(0)(0) = Option(King(color = false,moved = false))
    controller.chessBoard(0)(2) = Option(Queen(color = true,moved = false))

    expected.chessBoard =  new ChessBoardFactory().create(3)
    expected.chessBoard(0)(0) = Option(King(color = false,moved = false))
    expected.chessBoard(0)(2) = Option(Queen(color = true,moved = false))

    controller.move(2,0,1,2)
    "have no target koordinates" in {
      controller.chessBoard must be_==(expected.chessBoard)
    }
  }

  "A Controller" should{
    val controller = new ChessController
    val player = false
    val chessBoard = new ChessBoardFactory().create(3)
    chessBoard(1)(1) = Option(Pawn(color = true,moved = true))
    controller.currentPlayer = player
    controller.chessBoard = chessBoard
    controller.save()
    controller.load()
    "save and load correct" in {
     controller.currentPlayer must be_==(player)
     controller.chessBoard must be_==(chessBoard)
    }
  }

  "A Controller" should{
    val expected = new ChessController
    val controller = new ChessController
    controller.chessBoard =  new ChessBoardFactory().create(2)
    controller.chessBoard(0)(0) = Option(King(color = false,moved = false))
    controller.chessBoard(0)(1) = Option(Queen(color = true,moved = false))

    expected.chessBoard =  new ChessBoardFactory().create(2)
    expected.chessBoard(0)(0) = Option(King(color = false,moved = false))
    expected.chessBoard(0)(1) = Option(Queen(color = true,moved = false))

    controller.move(1,1,0,0)
    "have no source koordinates" in {
      controller.chessBoard must be_==(expected.chessBoard)
    }
  }
  "A Controller" should{
    val startChessboard = new ChessController
    val controller = new ChessController
    controller.chessBoard =  new ChessBoardFactory().create(2)
    controller.chessBoard(0)(0) = Option(King(color = false,moved = false))
    controller.chessBoard(0)(1) = Option(Queen(color = true,moved = false))
    controller.move(1,0,0,0)

    "reset chessboard after white check mate" in {
      controller.chessBoard must be_==(startChessboard.chessBoard)
    }
  }

  "A Controller" should{
    val startChessboard = new ChessController
    val controller = new ChessController
    controller.currentPlayer = false
    controller.chessBoard =  new ChessBoardFactory().create(2)
    controller.chessBoard(0)(0) = Option(King(color = true,moved = false))
    controller.chessBoard(0)(1) = Option(Queen(color = false,moved = false))
    controller.move(1,0,0,0)

    "reset chessboard after black check mate" in {
      controller.chessBoard must be_==(startChessboard.chessBoard)
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
    chessBoard(0)(0) = Option(Rook(color = true,moved = false))

    "have Rook on chessboard" in {
      controller.chessBoard(0)(0) must be_==(chessBoard(0)(0))
    }
  }


  "A Controller" should{
    val controller = new ChessController

    val chessBoard = new ChessBoardFactory().create(8)
    chessBoard(0)(0) = Option(Rook(color = true,moved = false))
    chessBoard(0)(1) = Option(Knight(color = true,moved = false))
    chessBoard(0)(2) = Option(Bishop(color = true,moved = false))
    chessBoard(0)(3) = Option(Queen(color = true,moved = false))
    chessBoard(0)(4) = Option(King(color = true,moved = false))
    chessBoard(0)(5) = Option(Bishop(color = true,moved = false))
    chessBoard(0)(6) = Option(Knight(color = true,moved = false))
    chessBoard(0)(7) = Option(Rook(color = true,moved = false))
    for(i <- 0 to 7){
      chessBoard(1)(i) = Option(Pawn(color = true,moved = false))
    }

    chessBoard(7)(0) = Option(Rook(color = false, moved = false))
    chessBoard(7)(1) = Option(Knight(color = false, moved = false))
    chessBoard(7)(2) = Option(Bishop(color = false, moved = false))
    chessBoard(7)(3) = Option(Queen(color = false, moved = false))
    chessBoard(7)(4) = Option(King(color = false, moved = false))
    chessBoard(7)(5) = Option(Bishop(color = false, moved = false))
    chessBoard(7)(6) = Option(Knight(color = false, moved = false))
    chessBoard(7)(7) = Option(Rook(color = false, moved = false))
    for(i <- 0 to 7){
      chessBoard(6)(i) = Option(Pawn(color = false, moved = false))
    }
    "have initialized chessboard" in {
      controller.chessBoard must be_==(chessBoard)
    }
  }

}