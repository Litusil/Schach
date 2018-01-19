package model

class ChessPieceFactory {
  def create(piece: String, hasMoved: Boolean): ChessPiece = {
    piece match {
      case "♖" => new Rook(true,hasMoved)
      case "♘" => new Knight(true,hasMoved)
      case "♗" => new Bishop(true,hasMoved)
      case "♕" => new Queen(true,hasMoved)
      case "♔" => new King(true,hasMoved)
      case "♙" => new Pawn(true,hasMoved)
      case "♜" => new Rook(false,hasMoved)
      case "♞" => new Knight(false,hasMoved)
      case "♝" => new Bishop(false,hasMoved)
      case "♛" => new Queen(false,hasMoved)
      case "♚" => new King(false,hasMoved)
      case "♟" => new Pawn(false,hasMoved)
    }
  }
}
