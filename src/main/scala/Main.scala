object Main {
    def main(args: Array[String]) {
        init()
        ChessBoard.print()
    }

    def init(): Unit ={

      ChessBoard.board(0)(0) = new Rook(true);
      ChessBoard.board(0)(1) = new Knight(true);
      ChessBoard.board(0)(2) = new Bishop(true);
      ChessBoard.board(0)(3) = new Queen(true);
      ChessBoard.board(0)(4) = new King(true);
      ChessBoard.board(0)(5) = new Bishop(true);
      ChessBoard.board(0)(6) = new Knight(true);
      ChessBoard.board(0)(7) = new Rook(true);
      for(i <- 0 to 7){
        ChessBoard.board(1)(i) = new Pawn(true);
      }

      ChessBoard.board(7)(0) = new Rook(false);
      ChessBoard.board(7)(1) = new Knight(false);
      ChessBoard.board(7)(2) = new Bishop(false);
      ChessBoard.board(7)(3) = new Queen(false);
      ChessBoard.board(7)(4) = new King(false);
      ChessBoard.board(7)(5) = new Bishop(false);
      ChessBoard.board(7)(6) = new Knight(false);
      ChessBoard.board(7)(7) = new Rook(false);
      for(i <- 0 to 7){
        ChessBoard.board(6)(i) = new Pawn(false);
      }

    }

}

