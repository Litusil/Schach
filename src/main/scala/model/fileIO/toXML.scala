package model.fileIO

import controller.ChessController
import model.{ChessBoardFactory, ChessPiece, ChessPieceFactory}

import scala.xml.PrettyPrinter

class toXML() extends fileIOInterface {

  override def save(chessBoard: Array[Array[ChessPiece]]): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("board.xml" ))
    val prettyPrinter = new PrettyPrinter(120,4)
    val xml = prettyPrinter.format(gridToXML(chessBoard))
    pw.write(xml)
    pw.close
  }

  def gridToXML(board: Array[Array[ChessPiece]]) = {
    <grid size ={board.size.toString}>
      {
      for {
        row <- 0 until board.size
        col <- 0 until board.size
      } yield cellToXml(board, row, col)
      }
    </grid>
  }

  def cellToXml(board: Array[Array[ChessPiece]], row: Int, col:Int) = {
    if (board(row)(col) != null) {
      <cell row={row.toString()} col={col.toString()} hasMoved={board(row)(col).hasMoved.toString()}>
        {board(row)(col)}
      </cell>
    }
  }


  override def load: Array[Array[ChessPiece]] = {

    var PieceFactory = new ChessPieceFactory
    val file = scala.xml.XML.loadFile("grid.xml")
    val sizeAttr = (file \\ "grid" \ "@size")
    val size = sizeAttr.text.toInt
    var chessBoard = new ChessBoardFactory().create(size)

    val cellNodes= (file \\ "cell")

    for (cell <- cellNodes) {
      val row: Int = (cell \ "@row").text.toInt
      val col: Int = (cell \ "@col").text.toInt
      val hasMoved = (cell \ "@hasMoved").text.toBoolean
      val piece = (cell).text
      chessBoard(row)(col) = PieceFactory.create(piece,hasMoved)
    }
    return chessBoard
  }

}
