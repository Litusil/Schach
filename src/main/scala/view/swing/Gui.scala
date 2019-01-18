package view.swing

import scala.swing._
import java.awt.Color
import scala.swing.BorderPanel.Position._
import controller.ChessController
import util.Observer
import scala.swing.event.ButtonClicked


case class Gui(controller: ChessController) extends MainFrame with Observer {

    controller.add(this)
    title = "Schach"
    preferredSize = new Dimension(750, 750)


    var fields: Array[Array[Field]] = Array.ofDim[Field](8,8)
    for(i <- controller.chessBoard.board.indices){
        for(j <- controller.chessBoard.board.indices) {

            if (((i + 1) % 2 )==1){
                if(((j+1) % 2) == 1){
                    if (controller.chessBoard.board(i)(j) != null) {
                        fields(i)(j) = new Field(controller.chessBoard.board(i)(j), Color.LIGHT_GRAY, controller, this,(i,j))
                    }else {
                        fields(i)(j) = new Field(null, Color.LIGHT_GRAY, controller, this,(i,j))
                    }
                }else {
                    if (controller.chessBoard.board(i)(j) != null) {
                        fields(i)(j) = new Field(controller.chessBoard.board(i)(j), Color.WHITE, controller, this,(i,j))
                    }else {
                        fields(i)(j) = new Field(null, Color.WHITE, controller, this,(i,j))
                    }
                }
            }else{
                if(((j+1) % 2) == 1){
                    if (controller.chessBoard.board(i)(j) != null){
                        fields(i)(j) = new Field(controller.chessBoard.board(i)(j), Color.WHITE, controller, this,(i,j))
                    }else {
                        fields(i)(j) = new Field(null, Color.WHITE, controller, this,(i,j))
                    }
                }else {
                    if (controller.chessBoard.board(i)(j) != null) {
                        fields(i)(j) = new Field(controller.chessBoard.board(i)(j), Color.LIGHT_GRAY, controller, this,(i,j))
                    }else {
                        fields(i)(j) = new Field(null,Color.LIGHT_GRAY, controller, this,(i,j))
                    }
                }
            }
        }
    }

  showEnemyPossibleAttacks(controller.chessBoard.getAttackMoves(!controller.chessBoard.currentPlayer))
  showMyPossibleAttacks(controller.chessBoard.getAttackMoves(controller.chessBoard.currentPlayer))

  var flowPanel: FlowPanel = new FlowPanel(FlowPanel.Alignment.Left)(){

    var speichern: Button = new Button("Speichern"){
      reactions += {
        case e: ButtonClicked => {
          controller.save()
          Dialog.showMessage(contents.head, "Progress Saved!", title="Save")
          }
        }
      }
    var laden: Button = new Button("Laden"){
      reactions += {
        case e: ButtonClicked => {
          controller.load()
        }
      }
    }
    contents += speichern
    contents += laden
  }

  var gridPanel: GridPanel = new GridPanel(8,8){
    for(i <- fields.indices){
      for(j <- fields.indices) {
        if(fields(i)(j) != null){
          contents += fields(i)(j)
        }
      }
    }
  }
  visible  = true

  contents = new BorderPanel {
    layout(gridPanel) = Center
    layout(flowPanel) = North
    //layout(textField) = South
  }

  def update(): Unit = {
    for (i <- controller.chessBoard.board.indices) {
      for (j <- controller.chessBoard.board.indices) {
        fields(i)(j).piece = controller.chessBoard.board(i)(j)
        fields(i)(j).update()
      }
    }
    if(controller.chessBoard.checkMate){
      if(controller.chessBoard.currentPlayer){
        Dialog.showMessage(contents.head, "Weiss hat gewonnen!", title="Checkmate")
      } else {
        Dialog.showMessage(contents.head, "Schwarz hat gewonnen!", title="Checkmate")
      }
    } else if(controller.chessBoard.whiteCheck && !controller.chessBoard.currentPlayer){
      Dialog.showMessage(contents.head, "Weiss steht im Schach!", title="Check")
    } else if(controller.chessBoard.blackCheck && controller.chessBoard.currentPlayer){
      Dialog.showMessage(contents.head, "Schwarz steht im Schach!!", title="Check")
    }

    showEnemyPossibleAttacks(controller.chessBoard.getAttackMoves(!controller.chessBoard.currentPlayer))
    showMyPossibleAttacks(controller.chessBoard.getAttackMoves(controller.chessBoard.currentPlayer))
  }

  def showPossibleMoves(possibleMoves: Vector[(Int,Int)]): Unit ={
      for(move <- possibleMoves ){
          fields(move._1)(move._2).background = Color.GREEN
      }
  }

  def hidePossibleMoves( possibleMoves: Vector[(Int,Int)]): Unit ={
    for(move <- possibleMoves ){
      fields(move._1)(move._2).background = fields(move._1)(move._2).color
    }
  }

  def showMyPossibleAttacks(possibleAttacks: Vector[(Int,Int)]): Unit ={
    for(move <- possibleAttacks ){
      if(fields(move._1)(move._2).background == Color.RED){
        fields(move._1)(move._2).background = Color.ORANGE
      } else {
        fields(move._1)(move._2).background = Color.YELLOW
      }

    }
  }

  def showEnemyPossibleAttacks(possibleAttacks: Vector[(Int,Int)]): Unit ={
    for(move <- possibleAttacks ){
      fields(move._1)(move._2).background = Color.RED
    }
  }
}
