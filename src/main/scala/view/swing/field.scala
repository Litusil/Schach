package view.swing

import java.awt.{Color, Font}
import controller.ChessController
import scala.swing.event._
import model.ChessPiece

import scala.collection.immutable.Vector
import scala.swing._

case class Field(var piece:ChessPiece, val color: Color,controller: ChessController, parentGui: Gui, val koordinates:(Int, Int)) extends Button{

  val markedColor = Color.GREEN
  var possibleMoves: Vector[(Int,Int)] = Vector()
  font = new Font("Arial Unicode MS", 0, 30)
  update()
  background = color

  reactions += {
    case e: ButtonClicked => {
      if(!Field.clickStatus){
        if(piece != null){
          possibleMoves = piece.getPossibleMoves(controller.chessBoard)
          parentGui.showPossibleMoves(possibleMoves)
          Field.clickStatus = true
          Field.selectedPiece = piece;
        }
      } else {
        val selectedPiecePos = Field.selectedPiece.getPosition(controller.chessBoard)
        controller.move(selectedPiecePos._2, selectedPiecePos._1, koordinates._2, koordinates._1)
        parentGui.hidePossibleMoves(possibleMoves)
        Field.clickStatus = false;
      }
    }
  }
  def update(): Unit ={
    if(piece == null){
      text = ""
    } else {
      text = piece.toString
    }
    background = color
  }
}

object Field{
  var selectedPiece:ChessPiece = null
  var clickStatus = false
}