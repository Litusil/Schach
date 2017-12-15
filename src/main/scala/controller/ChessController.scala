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

  def changePlayer() : Unit = {
    currentPlayer = !currentPlayer
  }

  def move(x_start: Int,y_start: Int,x_ziel: Int,y_ziel: Int): Unit ={
   /*
    println("x_start: "+x_start)
    println("y_start: "+y_start)
    println("x_ziel: "+x_ziel)
    println("y_ziel: "+y_ziel)
*/
    if(chessBoard(y_start)(x_start) == null || chessBoard(y_start)(x_start).color != currentPlayer) {
      println("Kein gültiger Zug!")
      notifyObservers()
      return
    }

    val moves = chessBoard(y_start)(x_start).getPossibleMoves(chessBoard)
    //moves.foreach{println}

    if (moves.contains((y_ziel,x_ziel))) {
      val kickedPiece = chessBoard(y_ziel)(x_ziel);
      chessBoard(y_ziel)(x_ziel) = chessBoard(y_start)(x_start);
      chessBoard(y_start)(x_start) = null;

      if (kickedPiece.isInstanceOf[King]) {
        if(currentPlayer){
          println("Winner Winner Chicken Dinner\n Weiß hat gewonnen!");
        } else {
          println("Winner Winner Chicken Dinner\n Schwarz hat gewonnen!");
        }
        chessBoard = new ChessBoardFactory().create(boardSize)
        init
      }

      changePlayer()
      notifyObservers()
    }else {
      println("Kein gültiger Zug! (Ziel koordinate)")
      notifyObservers()
    }
  }
}
