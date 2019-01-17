package model

trait ChessPiece{

  val color: Boolean
  var hasMoved: Boolean
  var position: (Int,Int)

  def getPossibleMoves(chessBoard: ChessBoard): Vector[(Int, Int)]

  def getPossibleAttacks(chessBoard: ChessBoard): Vector[(Int, Int)]
}
