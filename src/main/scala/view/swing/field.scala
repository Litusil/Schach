package view.swing

import java.awt.{Color, Font}

import controller.ChessController

import scala.swing.event._
import model.ChessPiece

import scala.swing._

case class Field(var piece:ChessPiece, val color: Color,controller: ChessController, parentGui: Gui) extends Button{

  val markedColor = Color.GREEN
  update()
  font = new Font("Arial Unicode MS", 0, 30)
  background = color

  reactions += {
    case e: ButtonClicked => {
      if(piece != null){
        //println(this.piece.toString)
        val possibleMoves = piece.getPossibleMoves(controller.chessBoard)
        parentGui.showPossibleMoves(possibleMoves)

      }
    }
  }









  def update(): Unit ={
    if(piece == null){
      text = ""
    } else {
      text = piece.toString
    }
  }
}
