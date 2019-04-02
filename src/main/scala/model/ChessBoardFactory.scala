package model

class ChessBoardFactory {

  def create(size: Int): Array[Array[Option[ChessPiece]]] = {
    val array = Array.ofDim[Option[ChessPiece]](size,size)
    for {
      i <- 0 to size-1
      j <- 0 to size-1
    } array(i)(j) = None
    array
  }
}
