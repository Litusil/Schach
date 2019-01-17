package view.swing

import java.awt.{Color, Font}
import controller.ChessController
import scala.swing.event._
import model.ChessPiece
import view.swing.clickstate.{ClickState, Clicked, NotClicked}

import scala.collection.immutable.Vector
import scala.swing._

case class Field(var piece:ChessPiece, val color: Color,controller: ChessController, parentGui: Gui, val koordinates:(Int, Int)) extends Button{

  val markedColor: Color = Color.GREEN
  var possibleMoves: Vector[(Int,Int)] = Vector()

  font = new Font("Arial Unicode MS", 0, 30)
  update()
  background = color

  reactions += {
    case e: ButtonClicked => {
      if(Field.clickState.isInstanceOf[NotClicked] && this.piece != null && this.piece.color == controller.chessBoard.currentPlayer) {
        Field.clickState.handle(this)
        Field.clickState = Field.clickState.nextState()

      } else if (Field.clickState.isInstanceOf[Clicked]){
        Field.clickState.handle(this)
        Field.clickState = Field.clickState.nextState()
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
  var clickState:ClickState = new NotClicked()
  var selectedPiece:ChessPiece = null
}