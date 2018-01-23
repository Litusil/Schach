package controller
import model._
import util.Observable


class ChessController(slmanager : fileIOInterface) extends Observable {

  var boardSize = 8;
  var chessBoard = new ChessBoardFactory().create(boardSize)
  var PieceFactory = new ChessPieceFactory
  init
  var currentPlayer  = true;


  def save(): Unit = {
    slmanager.save(this.chessBoard,this.currentPlayer)
  }

  def load():Unit = {
    var value = slmanager.load
    this.chessBoard = value._1
    this.currentPlayer = value._2
    notifyObservers()
  }

  def init(): Unit ={

    chessBoard(0)(0) = PieceFactory.create("♖",false)
    chessBoard(0)(1) = PieceFactory.create("♘",false)
    chessBoard(0)(2) = PieceFactory.create("♗",false)
    chessBoard(0)(3) = PieceFactory.create("♕",false)
    chessBoard(0)(4) = PieceFactory.create("♔",false)
    chessBoard(0)(5) = PieceFactory.create("♗",false)
    chessBoard(0)(6) = PieceFactory.create("♘",false)
    chessBoard(0)(7) = PieceFactory.create("♖",false)
    for(i <- 0 to 7){
      chessBoard(1)(i) = PieceFactory.create("♙",false)
    }

    chessBoard(7)(0) = PieceFactory.create("♜",false)
    chessBoard(7)(1) = PieceFactory.create("♞",false)
    chessBoard(7)(2) = PieceFactory.create("♝",false)
    chessBoard(7)(3) = PieceFactory.create("♛",false)
    chessBoard(7)(4) = PieceFactory.create("♚",false)
    chessBoard(7)(5) = PieceFactory.create("♝",false)
    chessBoard(7)(6) = PieceFactory.create("♞",false)
    chessBoard(7)(7) = PieceFactory.create("♜",false)
    for(i <- 0 to 7){
      chessBoard(6)(i) = PieceFactory.create("♟",false)
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
      chessBoard(y_ziel)(x_ziel).hasMoved = true
      changePlayer()
      notifyObservers()
    }else {
      println("Kein gültiger Zug! (Ziel koordinate)")
      notifyObservers()
    }
  }
}
