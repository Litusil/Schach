package model

class ChessPieceFactory {
  def create(piece: String, hasMoved: Boolean): ChessPiece = {
    piece match {
      case "♖" => Rook(color = true,hasMoved)
      case "♘" => Knight(color =true,hasMoved)
      case "♗" => Bishop(color =true,hasMoved)
      case "♕" => Queen(color =true,hasMoved)
      case "♔" => King(color =true,hasMoved)
      case "♙" => Pawn(color =true,hasMoved)
      case "♜" => Rook(color =false,hasMoved)
      case "♞" => Knight(color =false,hasMoved)
      case "♝" => Bishop(color =false,hasMoved)
      case "♛" => Queen(color =false,hasMoved)
      case "♚" => King(color =false,hasMoved)
      case "♟" => Pawn(color =false,hasMoved)
    }
  }
}
