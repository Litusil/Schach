package controller
import model._
import util.Observable


class ChessController extends Observable {

  var boardSize = 8;
  var chessBoard = new ChessBoardFactory().create(boardSize)
  init
  var currentPlayer  = true;


  def init(): Unit ={

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
  }

  def spielerwechsel() : Unit = {
    currentPlayer = !currentPlayer
  }

  def move(x_start: Int,y_start: Int,x_ziel: Int,y_ziel: Int): Unit ={
    if(chessBoard(y_start)(x_start).color != currentPlayer) {
      println("Falsche Farbe")
      return
    }

    val moves = chessBoard(y_start)(x_start).getPossibleMoves(chessBoard)
    //moves.foreach((v)=> println(v))
    println(chessBoard(y_start)(x_start).getPossibleMoves(chessBoard).size)
    println(y_start + ", " +x_start)
    println(y_ziel + ", " + x_ziel)

    if (moves.contains((y_ziel,x_ziel))) {
      chessBoard(y_ziel)(x_ziel) = chessBoard(y_start)(x_start);
      chessBoard(y_start)(x_start) = null;
      notifyObservers()
      println("klappt")
    }
    println("klappt nicht!")
  }

  def getCurrentPlayer: Boolean = {
      currentPlayer
  }
}
