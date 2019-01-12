package model

class ChessPieceFactory {
  def create(piece: String, hasMoved: Boolean, pos: (Int,Int)): ChessPiece = {
    piece match {
      case "♖" => Rook(color = true,hasMoved,pos)
      case "♘" => Knight(color =true,hasMoved,pos)
      case "♗" => Bishop(color =true,hasMoved,pos)
      case "♕" => Queen(color =true,hasMoved,pos)
      case "♔" => King(color =true,hasMoved,pos)
      case "♙" => Pawn(color =true,hasMoved,pos)
      case "♜" => Rook(color =false,hasMoved,pos)
      case "♞" => Knight(color =false,hasMoved,pos)
      case "♝" => Bishop(color =false,hasMoved,pos)
      case "♛" => Queen(color =false,hasMoved,pos)
      case "♚" => King(color =false,hasMoved,pos)
      case "♟" => Pawn(color =false,hasMoved,pos)
    }
  }
}
