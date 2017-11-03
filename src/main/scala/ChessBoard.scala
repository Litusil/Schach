object ChessBoard {
  var board = Array.ofDim[ChessPiece](8,8)

  def movePiece(): Unit ={

  }
  def interpCords(order:String): Unit ={
    Char
      order(1)
  }

  def print(): Unit ={
    for(i <- 0 to 7){
      for(j <- 0 to 7) {
        if(ChessBoard.board(i)(j) != null) {
          System.out.format("%3s", ChessBoard.board(i)(j).toString)
        } else {
          System.out.format("%3s", "X")
        }
      }
      System.out.println("\n")
    }
  }
}
