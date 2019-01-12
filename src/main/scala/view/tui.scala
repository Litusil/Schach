package view

import controller.ChessController
import model.ChessPiece
import util.Observer


class tui(controller: ChessController) extends Observer {

  controller.add(this)

  override def update(): Unit ={
    print()
  }

  def print(): String ={
    val xaxis = "   A|B| C| D|E| F| G|H" + "\n"
    val line = "|x" * 8 + "|\n"
    var board =  "\n"+ xaxis + ("y" + line) * 8

    for(i <- 0 to 7){
      for(j <- 0 to 7) {
        if(controller.chessBoard.board(i)(j) != null) {
          board = board.replaceFirst("x",controller.chessBoard.board(i)(j).toString)
        } else {
          board = board.replaceFirst("x","＿")
        }
        board = board.replaceFirst("y",(j + 1).toString)
      }
    }
    println(board)
    if(controller.currentPlayer) {
      println("Weiß ist am Zug: ")
    }else {
      println("Schwarz ist am Zug: ")
    }

    println(controller.chessBoard.whitePieces)
    println(controller.chessBoard.whitePiecesTaken)
    println("____________________")
    println(controller.chessBoard.blackPieces)
    println(controller.chessBoard.blackPiecesTaken)

    board
  }

  def processInputLine(eingabe: String): Unit ={
    if (eingabe.trim().toUpperCase.matches("[a-hA-H][1-8]( |-)[a-hA-H][1-8]")){
      var command = eingabe.trim().toUpperCase
      command = command.replaceAll("A","0")
      command = command.replaceAll("B","1")
      command = command.replaceAll("C","2")
      command = command.replaceAll("D","3")
      command = command.replaceAll("E","4")
      command = command.replaceAll("F","5")
      command = command.replaceAll("G","6")
      command = command.replaceAll("H","7")
      controller.move(command(0).toInt-48,command(1).toInt-49,command(3).toInt-48,command(4).toInt-49)
    }else {
      println("falsche Eingabe!\n")
    }
  }

}
