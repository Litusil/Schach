package controller
import com.google.inject.{Guice, Inject}
import net.codingwell.scalaguice.InjectorExtensions._
import model._
import model.fileIOComponent.FileIOInterface
import util.Observable



class ChessController extends Observable {


  val injector = Guice.createInjector(new SchachModule)
  val slmanager = injector.instance[FileIOInterface]
  var boardSize = 8
  var chessBoardFactory = new ChessBoardFactory
  var chessBoard: Array[Array[ChessPiece]] = chessBoardFactory.create(boardSize)
  var PieceFactory = new ChessPieceFactory
  init()
  var currentPlayer  = true


  def save(): Unit = {
    slmanager.save(this.chessBoard,this.currentPlayer)
  }

  def load():Unit = {
    val value = slmanager.load()
    this.chessBoard = value._1
    this.currentPlayer = value._2
    notifyObservers()
  }

  def init(): Unit ={

    chessBoard(0)(0) = PieceFactory.create("♖",hasMoved = false)
    chessBoard(0)(1) = PieceFactory.create("♘",hasMoved = false)
    chessBoard(0)(2) = PieceFactory.create("♗",hasMoved = false)
    chessBoard(0)(3) = PieceFactory.create("♕",hasMoved = false)
    chessBoard(0)(4) = PieceFactory.create("♔",hasMoved = false)
    chessBoard(0)(5) = PieceFactory.create("♗",hasMoved = false)
    chessBoard(0)(6) = PieceFactory.create("♘",hasMoved = false)
    chessBoard(0)(7) = PieceFactory.create("♖",hasMoved = false)
    for(i <- 0 to 7){
      chessBoard(1)(i) = PieceFactory.create("♙",hasMoved = false)
    }

    chessBoard(7)(0) = PieceFactory.create("♜",hasMoved = false)
    chessBoard(7)(1) = PieceFactory.create("♞",hasMoved = false)
    chessBoard(7)(2) = PieceFactory.create("♝",hasMoved = false)
    chessBoard(7)(3) = PieceFactory.create("♛",hasMoved = false)
    chessBoard(7)(4) = PieceFactory.create("♚",hasMoved = false)
    chessBoard(7)(5) = PieceFactory.create("♝",hasMoved = false)
    chessBoard(7)(6) = PieceFactory.create("♞",hasMoved = false)
    chessBoard(7)(7) = PieceFactory.create("♜",hasMoved = false)
    for(i <- 0 to 7){
      chessBoard(6)(i) = PieceFactory.create("♟",hasMoved = false)
    }
  }

  def changePlayer() : Unit = {
    currentPlayer = !currentPlayer
  }

  def move(x_start: Int,y_start: Int,x_ziel: Int,y_ziel: Int): Unit ={

    if(chessBoard(y_start)(x_start) == null || chessBoard(y_start)(x_start).color != currentPlayer) {
      println("Kein gültiger Zug!")
      notifyObservers()
      return
    }

    val moves = chessBoard(y_start)(x_start).getPossibleMoves(chessBoard)
    //moves.foreach{println}

    if (moves.contains((y_ziel,x_ziel))) {
      val kickedPiece = chessBoard(y_ziel)(x_ziel)
      chessBoard(y_ziel)(x_ziel) = chessBoard(y_start)(x_start)
      chessBoard(y_start)(x_start) = null

      if (kickedPiece.isInstanceOf[King]) {
        if(currentPlayer){
          println("Winner Winner Chicken Dinner\n Weiß hat gewonnen!")
        } else {
          println("Winner Winner Chicken Dinner\n Schwarz hat gewonnen!")
        }
        chessBoard = new ChessBoardFactory().create(boardSize)
        init()
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
