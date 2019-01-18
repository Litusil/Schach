package controller
import com.google.inject.{Guice, Inject}
import net.codingwell.scalaguice.InjectorExtensions._
import model._
import model.fileIOComponent.FileIOInterface
import util.Observable

import scala.collection.immutable.Vector



class ChessController extends Observable {


  val injector = Guice.createInjector(new SchachModule)
  val slmanager = injector.instance[FileIOInterface]
  var chessBoard : ChessBoard = null
  newGame()


  def newGame(): Unit = {
    chessBoard = new ChessBoard()
    chessBoard.init(8)
  }

  def save(): Unit = {
    slmanager.save(chessBoard,"test.json")
  }

  def load():Unit = {
    chessBoard = slmanager.load("test.json")
    notifyObservers()
  }

  def move(cb: ChessBoard, x_start: Int,y_start: Int,x_ziel: Int, y_ziel: Int): ChessBoard ={

    if(cb.board(y_start)(x_start) == null || cb.board(y_start)(x_start).color != cb.currentPlayer) {
      println("Kein gültiger Zug!")
      return cb
    }

    var validMoves: Vector[(Int, Int)] = Vector()

    validMoves = cb.board(y_start)(x_start).getPossibleMoves(cb)

    if (validMoves.contains((y_ziel,x_ziel))) {
      val kickedPiece = cb.board(y_ziel)(x_ziel)

      if(kickedPiece != null){
        if(chessBoard.currentPlayer){
          cb.blackPiecesTaken = cb.blackPiecesTaken :+ kickedPiece
          kickedPiece.position = (-1,-1)
          cb.blackPieces = cb.blackPieces.filterNot(_ == kickedPiece)
        } else {
          cb.whitePiecesTaken = cb.whitePiecesTaken :+ kickedPiece
          kickedPiece.position = (-1,-1)
          cb.whitePieces = cb.whitePieces.filterNot(_ == kickedPiece)
        }
      }

      cb.board(y_ziel)(x_ziel) = cb.board(y_start)(x_start)
      cb.board(y_ziel)(x_ziel).position = (y_ziel,x_ziel)
      cb.board(y_start)(x_start) = null
      cb.board(y_ziel)(x_ziel).hasMoved = true


      if(cb.currentPlayer){
        if(cb.isBlackCheck()){
          if(cb.isBlackCheckmate()){
            notifyObservers()
            newGame()
            notifyObservers()
            return this.chessBoard
          }
        }
      } else {
        if(cb.isWhiteCheck()){
          if(cb.isWhiteCheckmate()){
            notifyObservers()
            newGame()
            notifyObservers()
            return this.chessBoard
          }
        }
      }

      if(cb.whiteCheck || cb.isBlackCheck()){
        notifyObservers()
        cb.changePlayer()
        notifyObservers()
      }
      else {
        cb.changePlayer()
        notifyObservers()
      }

    } else {
      println("Kein gültiger Zug!")
      notifyObservers()
    }
    cb
  }
}
