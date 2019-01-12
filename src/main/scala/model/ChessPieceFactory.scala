package model

class ChessPieceFactory {
  def create(piece: String, hasMoved: Boolean, pos: (Int,Int)): ChessPiece = {
    piece match {
      case "♖" => new Rook(color = true,hasMoved,pos)
      case "♘" => new Knight(color =true,hasMoved,pos)
      case "♗" => new Bishop(color =true,hasMoved,pos)
      case "♕" => new Queen(color =true,hasMoved,pos)
      case "♔" => new King(color =true,hasMoved,pos)
      case "♙" => new Pawn(color =true,hasMoved,pos)
      case "♜" => new Rook(color =false,hasMoved,pos)
      case "♞" => new Knight(color =false,hasMoved,pos)
      case "♝" => new Bishop(color =false,hasMoved,pos)
      case "♛" => new Queen(color =false,hasMoved,pos)
      case "♚" => new King(color =false,hasMoved,pos)
      case "♟" => new Pawn(color =false,hasMoved,pos)
    }
  }
}
