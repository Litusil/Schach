package model

import scala.collection.immutable.Vector

class ChessBoard () {
  var board: Array[Array[ChessPiece]] = null
  var whitePieces: Vector[(ChessPiece)] = Vector()
  var blackPieces: Vector[(ChessPiece)] = Vector()
  var whitePiecesTaken: Vector[(ChessPiece)] = Vector()
  var blackPiecesTaken: Vector[(ChessPiece)] = Vector()
  var currentPlayer  = true
  var boardSize: Int = 0;
  val chessBoardFactory = new ChessBoardFactory
  val PieceFactory = new ChessPieceFactory

  def changePlayer() : Unit = {
    currentPlayer = !currentPlayer
  }

  def copy(boardToCopy: ChessBoard): Unit ={

    currentPlayer = boardToCopy.currentPlayer
    boardSize = boardToCopy.boardSize
    board = chessBoardFactory.create(boardSize)
    for(y<-boardToCopy.whitePieces){
      whitePieces = whitePieces :+ PieceFactory.create(y.toString,hasMoved = y.hasMoved,(y.position._1,y.position._2))
    }
    for(y<-boardToCopy.whitePiecesTaken){
      whitePiecesTaken = whitePiecesTaken :+ PieceFactory.create(y.toString,hasMoved = y.hasMoved,(y.position._1,y.position._2))
    }
    for(y<-boardToCopy.blackPieces){
      blackPieces = blackPieces :+ PieceFactory.create(y.toString,hasMoved = y.hasMoved,(y.position._1,y.position._2))
    }
    for(y<-boardToCopy.blackPiecesTaken){
      blackPiecesTaken = blackPiecesTaken :+ PieceFactory.create(y.toString,hasMoved = y.hasMoved,(y.position._1,y.position._2))
    }
    for(y<-whitePieces){
      board(y.position._1)(y.position._2) = y
    }
    for(y<-whitePieces){
      board(y.position._1)(y.position._2) = y
    }
  }

  def init(boardSize: Int): Unit ={
    this.boardSize = boardSize
    board = chessBoardFactory.create(this.boardSize)

    if(boardSize == 8){
      board(0)(0) = PieceFactory.create("♖",hasMoved = false,(0,0))
      whitePieces = whitePieces :+ (board(0)(0))
      board(0)(1) = PieceFactory.create("♘",hasMoved = false,(0,1))
      whitePieces = whitePieces :+ (board(0)(1))
      board(0)(2) = PieceFactory.create("♗",hasMoved = false,(0,2))
      whitePieces = whitePieces :+ (board(0)(2))
      board(0)(3) = PieceFactory.create("♕",hasMoved = false,(0,3))
      whitePieces = whitePieces :+ (board(0)(3))
      board(0)(4) = PieceFactory.create("♔",hasMoved = false,(0,4))
      whitePieces = whitePieces :+ (board(0)(4))
      board(0)(5) = PieceFactory.create("♗",hasMoved = false,(0,5))
      whitePieces = whitePieces :+ (board(0)(5))
      board(0)(6) = PieceFactory.create("♘",hasMoved = false,(0,6))
      whitePieces = whitePieces :+ (board(0)(6))
      board(0)(7) = PieceFactory.create("♖",hasMoved = false,(0,7))
      whitePieces = whitePieces :+ (board(0)(7))
      for(i <- 0 to 7){
        board(1)(i) = PieceFactory.create("♙",hasMoved = false, (1,i))
        whitePieces = whitePieces :+ (board(1)(i))
      }

      board(7)(0) = PieceFactory.create("♜",hasMoved = false, (7,0))
      blackPieces = blackPieces :+ (board(7)(0))
      board(7)(1) = PieceFactory.create("♞",hasMoved = false, (7,1))
      blackPieces = blackPieces :+ (board(7)(1))
      board(7)(2) = PieceFactory.create("♝",hasMoved = false, (7,2))
      blackPieces = blackPieces :+ (board(7)(2))
      board(7)(3) = PieceFactory.create("♛",hasMoved = false, (7,3))
      blackPieces = blackPieces :+ (board(7)(3))
      board(7)(4) = PieceFactory.create("♚",hasMoved = false, (7,4))
      blackPieces = blackPieces :+ (board(7)(4))
      board(7)(5) = PieceFactory.create("♝",hasMoved = false, (7,5))
      blackPieces = blackPieces :+ (board(7)(5))
      board(7)(6) = PieceFactory.create("♞",hasMoved = false, (7,6))
      blackPieces = blackPieces :+ (board(7)(6))
      board(7)(7) = PieceFactory.create("♜",hasMoved = false, (7,7))
      blackPieces = blackPieces :+ (board(7)(7))
      for(i <- 0 to 7){
        board(6)(i) = PieceFactory.create("♟",hasMoved = false, (6,i))
        blackPieces = blackPieces :+ (board(6)(i))
      }
    }
  }
}
