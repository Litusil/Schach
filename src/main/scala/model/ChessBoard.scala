package model

import scala.collection.immutable.Vector

class ChessBoard (val boardSize: Int) {
  var chessBoardFactory = new ChessBoardFactory
  var board: Array[Array[ChessPiece]] = chessBoardFactory.create(boardSize)
  var PieceFactory = new ChessPieceFactory
  var whitePieces: Vector[(ChessPiece)] = Vector()
  var blackPieces: Vector[(ChessPiece)] = Vector()
  var whitePiecesTaken: Vector[(ChessPiece)] = Vector()
  var blackPiecesTaken: Vector[(ChessPiece)] = Vector()

  if(this.boardSize == 8){
    init()
  }

  def init(): Unit ={

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
