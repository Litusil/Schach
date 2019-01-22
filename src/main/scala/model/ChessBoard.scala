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
  var whiteCheck = false
  var blackCheck = false
  var checkMate = false
  var simulated = false

  def changePlayer() : Unit = {
    currentPlayer = !currentPlayer
  }

  //Die Werte eines anderen Schachbretts werden in das aktuelle übernommen
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
    for(y<-blackPieces){
      board(y.position._1)(y.position._2) = y
    }
    whiteCheck = boardToCopy.whiteCheck
    blackCheck = boardToCopy.blackCheck
  }

  // Hiermit Simulieren wir den nächsten Status dieses Schachbretts nach einem bestimmten move
  def simulate(x_start: Int,y_start: Int,x_ziel: Int,y_ziel: Int): ChessBoard ={

    val simulatedBoard = new ChessBoard
    simulatedBoard.copy(this)
    simulatedBoard.simulated = true
    simulatedBoard.changePlayer()

    val kickedPiece = simulatedBoard.board(y_ziel)(x_ziel)
    if(kickedPiece != null){
      if(simulatedBoard.currentPlayer){
        simulatedBoard.blackPiecesTaken = simulatedBoard.blackPiecesTaken :+ kickedPiece
        kickedPiece.position = (-1,-1)
        simulatedBoard.blackPieces = simulatedBoard.blackPieces.filterNot(_ == kickedPiece)
      } else {
        simulatedBoard.whitePiecesTaken = simulatedBoard.whitePiecesTaken :+ kickedPiece
        kickedPiece.position = (-1,-1)
        simulatedBoard.whitePieces = simulatedBoard.whitePieces.filterNot(_ == kickedPiece)
      }
    }

    simulatedBoard.board(y_ziel)(x_ziel) = simulatedBoard.board(y_start)(x_start)
    simulatedBoard.board(y_ziel)(x_ziel).position = (y_ziel,x_ziel)
    simulatedBoard.board(y_start)(x_start) = null
    simulatedBoard.board(y_ziel)(x_ziel).hasMoved = true

    simulatedBoard.changePlayer()

    simulatedBoard
  }

  def getAttackMoves(color: Boolean): Vector[(Int,Int)] = {
    var possibleAttacks: Vector[(Int,Int)] = Vector()
    if(color){
      for(y <- whitePieces){
        possibleAttacks = possibleAttacks ++ y.getPossibleAttacks(this)
      }
    } else {
      for(y <- blackPieces){
        possibleAttacks = possibleAttacks ++ y.getPossibleAttacks(this)
      }
    }
    possibleAttacks
  }

  def isWhiteCheckmate(): Boolean ={
    var possibleAttacks: Vector[(Int,Int)] = getAttackMoves(false)
    this.changePlayer()
    for(y <- whitePieces){
      possibleAttacks = possibleAttacks ++ y.getPossibleMoves(this)
    }
    this.changePlayer()
    if(possibleAttacks.length == 0){
      checkMate = true
    }
    checkMate
  }


  def isBlackCheckmate(): Boolean ={
    var possibleAttacks: Vector[(Int,Int)] = Vector()
    this.changePlayer()
    for(y <- blackPieces){
      possibleAttacks = possibleAttacks ++ y.getPossibleMoves(this)
    }
    this.changePlayer()
    if(possibleAttacks.length == 0){
      checkMate = true
    }
    checkMate
  }

  def isWhiteCheck(): Boolean ={
    var possibleAttacks: Vector[(Int,Int)] = getAttackMoves(false)

    for(y <- whitePieces){
      if(y.toString == "\u2654"){
        if( possibleAttacks.contains(y.position)){
          return true
        }
      }
    }
    false
  }

  def isBlackCheck(): Boolean ={
    val possibleAttacks: Vector[(Int,Int)] = getAttackMoves(true)

    for(y <- blackPieces){
      if(y.toString == "\u265A"){
        if( possibleAttacks.contains(y.position)){
          return true
        }
      }
    }
    false
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

  override def toString: String = {
    val xaxis = "   A|B| C| D|E| F| G|H" + "\n"
    val line = "|x" * 8 + "|\n"
    var board =  "\n"+ xaxis + ("y" + line) * 8

    for(i <- 0 to 7){
      for(j <- 0 to 7) {
        if(this.board(i)(j) != null) {
          board = board.replaceFirst("x",this.board(i)(j).toString)
        } else {
          board = board.replaceFirst("x","＿")
        }
        board = board.replaceFirst("y",(j + 1).toString)
      }
    }
    println(board)
    if(this.currentPlayer) {
      println("Weiß ist am Zug: ")
    }else {
      println("Schwarz ist am Zug: ")
    }

    println(this.whitePieces)
    println(this.whitePiecesTaken)
    println("____________________")
    println(this.blackPieces)
    println(this.blackPiecesTaken)

    board
  }

}
