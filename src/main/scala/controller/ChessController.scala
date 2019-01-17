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
  var check = false
  var checkMate = false;
  newGame()



  def newGame(): Unit = {
    chessBoard = new ChessBoard()
    chessBoard.init(8)
    check = false
    checkMate = false
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

  def getAttackMoves(color: Boolean): Vector[(Int,Int)] = {
    var possibleAttacks: Vector[(Int,Int)] = Vector()
    if(color){
      for(y <- chessBoard.whitePieces){
        val attackVector = y.getPossibleAttacks(chessBoard.board)
          possibleAttacks = possibleAttacks ++ y.getPossibleAttacks(chessBoard.board)
      }
    } else {
      for(y <- chessBoard.blackPieces){
        possibleAttacks = possibleAttacks ++ y.getPossibleAttacks(chessBoard.board)
      }
    }
    possibleAttacks
  }
  def isCheck(board: ChessBoard): Boolean ={
    var possibleAttacks: Vector[(Int,Int)] = getAttackMoves(board.currentPlayer)
    if(board.currentPlayer){
      for(y <- board.blackPieces){
        if(y.toString == "\u265A"){
          if( possibleAttacks.contains(y.position)){
            check = true;
            return true
          }
        }
      }
    } else {
      for(y <- board.whitePieces){
        if(y.toString == "\u2654"){
          if( possibleAttacks.contains(y.position)){
            check = true;
            return true
          }
        }
      }
    }
    false
  }

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

  def simulate( chessBoard: ChessBoard, x_start: Int,y_start: Int,x_ziel: Int,y_ziel: Int): ChessBoard ={
    chessBoard.currentPlayer = !chessBoard.currentPlayer
    move(chessBoard,x_start,y_start,x_ziel,y_ziel,true)
  }

  def move( chessBoard: ChessBoard, x_start: Int,y_start: Int,x_ziel: Int, y_ziel: Int, sim: Boolean): ChessBoard ={

    if(chessBoard.board(y_start)(x_start) == null || chessBoard.board(y_start)(x_start).color != chessBoard.currentPlayer) {
      println("Kein gültiger Zug!")
      return chessBoard
    }

    val moves = chessBoard.board(y_start)(x_start).getPossibleMoves(chessBoard.board)

    if (moves.contains((y_ziel,x_ziel))) {

      val kickedPiece = chessBoard.board(y_ziel)(x_ziel)

      if(kickedPiece != null){
        if(chessBoard.currentPlayer){
          chessBoard.blackPiecesTaken = chessBoard.blackPiecesTaken :+ kickedPiece
          kickedPiece.position = (-1,-1)
          chessBoard.blackPieces = chessBoard.blackPieces.filterNot(_ == kickedPiece)
        } else {
          chessBoard.whitePiecesTaken = chessBoard.whitePiecesTaken :+ kickedPiece
          kickedPiece.position = (-1,-1)
          chessBoard.whitePieces = chessBoard.whitePieces.filterNot(_ == kickedPiece)
        }
      }

      chessBoard.board(y_ziel)(x_ziel) = chessBoard.board(y_start)(x_start)
      chessBoard.board(y_ziel)(x_ziel).position = (y_ziel,x_ziel)
      chessBoard.board(y_start)(x_start) = null
      chessBoard.board(y_ziel)(x_ziel).hasMoved = true

      if(isCheck(chessBoard)){
        if(isCheckmate()){
         checkMate = true
        }
      }

      chessBoard.changePlayer()
      if(!sim){
        notifyObservers()
        if(checkMate){
          newGame()
          notifyObservers()
        }
      }
    } else {
      println("Kein gültiger Zug!")
      //notifyObservers()
    }
    chessBoard
  }
}
