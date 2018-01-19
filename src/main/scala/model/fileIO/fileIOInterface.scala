package model.fileIO

import model.ChessPiece

trait fileIOInterface {

  def load:Array[Array[ChessPiece]]
  def save(chessBoard: Array[Array[ChessPiece]]):Unit

}