package model.fileIOComponent

import model.ChessPiece

trait FileIOInterface {

  def load():(Array[Array[ChessPiece]],Boolean)
  def save(board: Array[Array[ChessPiece]],player: Boolean): Unit

}