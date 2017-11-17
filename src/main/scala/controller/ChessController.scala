package controller
import model._

class ChessController {

  var boardSize = 8;
  var chessBoard = new ChessBoardFactory().create(boardSize);
  init();

  def init(): Unit ={

    chessBoard(0)(0) = new Rook(true);
    chessBoard(0)(1) = new Knight(true);
    chessBoard(0)(2) = new Bishop(true);
    chessBoard(0)(3) = new Queen(true);
    chessBoard(0)(4) = new King(true);
    chessBoard(0)(5) = new Bishop(true);
    chessBoard(0)(6) = new Knight(true);
    chessBoard(0)(7) = new Rook(true);
    for(i <- 0 to 7){
      chessBoard(1)(i) = new Pawn(true);
    }

    chessBoard(7)(0) = new Rook(false);
    chessBoard(7)(1) = new Knight(false);
    chessBoard(7)(2) = new Bishop(false);
    chessBoard(7)(3) = new Queen(false);
    chessBoard(7)(4) = new King(false);
    chessBoard(7)(5) = new Bishop(false);
    chessBoard(7)(6) = new Knight(false);
    chessBoard(7)(7) = new Rook(false);
    for(i <- 0 to 7){
      chessBoard(6)(i) = new Pawn(false);
    }
  }
  def print(): Unit ={
    for(i <- 0 to 7){
      for(j <- 0 to 7) {
        if(chessBoard(i)(j) != null) {
          System.out.format("%3s", chessBoard(i)(j).toString)
        } else {
          System.out.format("%3s","\u23f9")
        }
      }
      System.out.println("\n")
    }
  }

  /*
  def movePiece(): Unit ={

  }
  def interpCords(order:String): Unit ={
    Char
      order(1)
  }


  */


}
