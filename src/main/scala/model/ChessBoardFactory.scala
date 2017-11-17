package model

class ChessBoardFactory {


  def create(size: Int): Array[Array[ChessPiece]] = {
    return Array.ofDim[ChessPiece](size,size)
  }

}
