package view.swing.clickstate

import view.swing.Field

class Clicked() extends ClickState{
  override def handle(field:Field): Unit = {
    val selectedPiecePos = Field.selectedPiece.position
    field.controller.chessBoard = field.controller.move(field.controller.chessBoard,selectedPiecePos._2, selectedPiecePos._1, field.koordinates._2, field.koordinates._1)
    field.parentGui.hidePossibleMoves(field.possibleMoves)
  }

  override def nextState():ClickState = {
    new NotClicked()
  }

  override def toString: String = "clicked"
}
