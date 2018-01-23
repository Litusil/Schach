package model

import controller.ChessController

import scala.xml.PrettyPrinter

class toXML extends fileIOInterface {

  override def save(board: Array[Array[ChessPiece]],player: Boolean): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("board.xml" ))
    val prettyPrinter = new PrettyPrinter(120,4)
    val xml = prettyPrinter.format(gridToXML(board,player))
    pw.write(xml)
    pw.close
  }

  def gridToXML(board: Array[Array[ChessPiece]],player: Boolean) = {
    <grid size ={board.size.toString} player = {player.toString}>
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


  override def load: (Array[Array[ChessPiece]],Boolean) = {

    var PieceFactory = new ChessPieceFactory
    val file = scala.xml.XML.loadFile("config.xml")
    val sizeAttr = (file \\ "grid" \ "@size")
    val size = sizeAttr.text.toInt
    var chessBoard = new ChessBoardFactory().create(size)
    var currentPlayer = (file \\ "grid" \ "@player").text.toBoolean

    val cellNodes= (file \\ "cell")

    for (cell <- cellNodes) {
      val row: Int = (cell \ "@row").text.toInt
      val col: Int = (cell \ "@col").text.toInt
      val hasMoved = (cell \ "@hasMoved").text.toBoolean
      val piece = (cell).text.trim
      chessBoard(row)(col) = PieceFactory.create(piece,hasMoved)
    }
    return (chessBoard,currentPlayer)
  }

}
