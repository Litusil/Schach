package model.fileIOComponent

import model.{ChessBoard, ChessPiece}

trait FileIOInterface {

  def load(fileName: String): ChessBoard
  def save(board: ChessBoard,fileName: String ): Unit

}