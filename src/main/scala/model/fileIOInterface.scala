package model

trait fileIOInterface {

  def load():(Array[Array[ChessPiece]],Boolean)
  def save(board: Array[Array[ChessPiece]],player: Boolean): Unit

}