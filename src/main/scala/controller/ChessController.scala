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
    slmanager.save(this.chessBoard.board,this.chessBoard.currentPlayer)
  }

  def load():Unit = {
    val value = slmanager.load()
    this.chessBoard.board = value._1
    this.chessBoard.currentPlayer = value._2
    notifyObservers()
  }

  /*
  def isCheckmate() : Boolean ={
    var checkmate: Boolean = true;
    if(chessBoard.currentPlayer){
      for(piece <- chessBoard.blackPieces){
        for(moves <- piece.getPossibleMoves(chessBoard.board)){
          var simulatedBoard = new ChessBoard
          simulatedBoard.copy(chessBoard)
          simulatedBoard = simulate(simulatedBoard,piece.position._2,piece.position._1,moves._2,moves._1)
          if(!isCheck(simulatedBoard)){
            checkmate = false;
            return checkmate
          }
        }
        }
    } else {
      for(piece <- chessBoard.whitePieces){
        for(moves <- piece.getPossibleMoves(chessBoard.board)){
          var simulatedBoard = new ChessBoard
          simulatedBoard.copy(chessBoard)
          simulatedBoard = simulate(simulatedBoard,piece.position._2,piece.position._1,moves._2,moves._1)
          if(!isCheck(simulatedBoard)){
            checkmate = false;
            return checkmate
          }
        }
      }
    }
    checkmate
  }
  */

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
        cb.isBlackCheck()
      } else {
        cb.isWhiteCheck()
      }
        /*
        if(isCheckmate()){
          cb.checkMate = true
        }
        */

      notifyObservers()
      cb.changePlayer()
      notifyObservers()
      if(cb.checkMate){
        newGame()
        notifyObservers()
      }
    } else {
      println("Kein gültiger Zug!")
      notifyObservers()
    }
    cb
  }
}
