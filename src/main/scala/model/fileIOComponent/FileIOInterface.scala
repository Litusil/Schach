package model.fileIOComponent

import model.ChessPiece

trait FileIOInterface {

  def load():(Array[Array[Option[ChessPiece]]],Boolean)
  def save(board: Array[Array[Option[ChessPiece]]],player: Boolean): Unit

}