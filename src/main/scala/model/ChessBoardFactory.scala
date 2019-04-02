package model

class ChessBoardFactory {

  def create(size: Int): Array[Array[ChessPiece]] = {
    Array.ofDim[ChessPiece](size,size)
  }
}
