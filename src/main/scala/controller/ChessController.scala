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
  var chessBoard = new ChessBoard(8)
  var currentPlayer  = true

  def save(): Unit = {
    slmanager.save(this.chessBoard.board,this.currentPlayer)
  }

  def load():Unit = {
    val value = slmanager.load()
    this.chessBoard.board = value._1
    this.currentPlayer = value._2
    notifyObservers()
  }

  def changePlayer() : Unit = {
    currentPlayer = !currentPlayer
  }

  def getEnemyMoves(): Vector[(Int,Int)] = {
    var possibleAttacks: Vector[(Int,Int)] = Vector()
    if(currentPlayer){
      for(y <- chessBoard.blackPieces){
        val attackVector = y.getPossibleAttacks(chessBoard.board)
          possibleAttacks = possibleAttacks ++ y.getPossibleAttacks(chessBoard.board)
      }
    } else {
      for(y <- chessBoard.whitePieces){
        possibleAttacks = possibleAttacks ++ y.getPossibleAttacks(chessBoard.board)
      }
    }
    possibleAttacks
  }

  def move( chessBoard: ChessBoard, x_start: Int,y_start: Int,x_ziel: Int,y_ziel: Int): ChessBoard ={

    if(chessBoard.board(y_start)(x_start) == null || chessBoard.board(y_start)(x_start).color != currentPlayer) {
      println("Kein gültiger Zug!")
      notifyObservers()
      return
    }

    val moves = chessBoard.board(y_start)(x_start).getPossibleMoves(chessBoard.board)

    if (moves.contains((y_ziel,x_ziel))) {

      val kickedPiece = chessBoard.board(y_ziel)(x_ziel)

      if(kickedPiece != null){
        if(currentPlayer){
          chessBoard.blackPiecesTaken = chessBoard.blackPiecesTaken :+ kickedPiece
          chessBoard.blackPieces = chessBoard.blackPieces.filterNot(_ == kickedPiece)
        } else {
          chessBoard.whitePiecesTaken = chessBoard.whitePiecesTaken :+ kickedPiece
          chessBoard.whitePieces = chessBoard.whitePieces.filterNot(_ == kickedPiece)
        }

        /*
        if (kickedPiece.isInstanceOf[King]) {
          if(currentPlayer){
            println("Winner Winner Chicken Dinner\n Weiß hat gewonnen!")
          } else {
            println("Winner Winner Chicken Dinner\n Schwarz hat gewonnen!")
          }
          chessBoard = new ChessBoard(8)
          notifyObservers()
          this.currentPlayer = true
          return
        }
        */
      }

      chessBoard.board(y_ziel)(x_ziel) = chessBoard.board(y_start)(x_start)
      chessBoard.board(y_ziel)(x_ziel).position = (y_ziel,x_ziel)
      chessBoard.board(y_start)(x_start) = null
      chessBoard.board(y_ziel)(x_ziel).hasMoved = true

      changePlayer()
      notifyObservers()
    }else {
      println("Kein gültiger Zug! (Ziel koordinate)")
      notifyObservers()
    }
  }
}
