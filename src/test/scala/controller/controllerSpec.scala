package controller

import model._
import model.fileIOComponent.FileIOInterface
import model.fileIOComponent.fileIoXmlImpl.FileIO
import org.specs2.mutable._

import scala.collection.immutable.Vector


class controllerSpec extends Specification{

  var slmanager:FileIOInterface = new FileIO

  "A Controller" should{
    val controller = new ChessController

    var chessBoard = ChessBoard(Vector.fill(3,3)(None: Option[ChessPiece]))
    chessBoard = chessBoard.updatePlayer(false)
    chessBoard = chessBoard.putPiece(1,1,Option(Pawn(color = true,hasMoved = true)))
    controller.chessBoard = chessBoard
    controller.save()
    controller.load()
    "save and load correct" in {
     controller.chessBoard must be_==(chessBoard)
    }
  }

  "A Controller" should{
    val controller = new ChessController
    val chessBoard = new ChessBoard(Vector.fill(8,8)(None: Option[ChessPiece])).defaultInit()

    "have initialized chessboard" in {
      controller.chessBoard must be_==(chessBoard)
    }
  }

}