package view.swing

import model.toXML
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


    var fields = Array.ofDim[Field](8,8)
    for(i <- 0 to controller.chessBoard.size-1){
        for(j <- 0 to controller.chessBoard.size-1) {

            if (((i + 1) % 2 )==1){
                if(((j+1) % 2) == 1){
                    if (controller.chessBoard(i)(j) != null) {
                        fields(i)(j) = new Field(controller.chessBoard(i)(j), Color.LIGHT_GRAY, controller, this,(i,j))
                    }else {
                        fields(i)(j) = new Field(null, Color.LIGHT_GRAY, controller, this,(i,j))
                    }
                }else {
                    if (controller.chessBoard(i)(j) != null) {
                        fields(i)(j) = new Field(controller.chessBoard(i)(j), Color.WHITE, controller, this,(i,j))
                    }else {
                        fields(i)(j) = new Field(null, Color.WHITE, controller, this,(i,j))
                    }
                }
            }else{
                if(((j+1) % 2) == 1){
                    if (controller.chessBoard(i)(j) != null){
                        fields(i)(j) = new Field(controller.chessBoard(i)(j), Color.WHITE, controller, this,(i,j))
                    }else {
                        fields(i)(j) = new Field(null, Color.WHITE, controller, this,(i,j))
                    }
                }else {
                    if (controller.chessBoard(i)(j) != null) {
                        fields(i)(j) = new Field(controller.chessBoard(i)(j), Color.LIGHT_GRAY, controller, this,(i,j))
                    }else {
                        fields(i)(j) = new Field(null,Color.LIGHT_GRAY, controller, this,(i,j))
                    }
                }
            }
        }
    }


  var flowPanel = new FlowPanel(FlowPanel.Alignment.Left)(){

    var speichern = new Button("Speichern"){
      reactions += {
        case e: ButtonClicked => {
          controller.save
          Dialog.showMessage(contents.head, "Progress Saved!", title="Save")
          }
        }
      }
    var laden = new Button("Laden"){
      reactions += {
        case e: ButtonClicked => {
          controller.load()
        }
      }
    }
    contents += speichern
    contents += laden



  }


  var gridPanel = new GridPanel(8,8){
    for(i <- 0 to fields.size-1){
      for(j <- 0 to fields.size-1) {
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
      for (i <- 0 to controller.chessBoard.size - 1) {
        for (j <- 0 to controller.chessBoard.size - 1) {
          fields(i)(j).piece = controller.chessBoard(i)(j)
          fields(i)(j).update()
        }
      }
    }

    def showPossibleMoves( possibleMoves: Vector[(Int,Int)]): Unit ={
        for(move <- possibleMoves ){
            fields(move._1)(move._2).background = Color.GREEN
        }
    }

  def hidePossibleMoves( possibleMoves: Vector[(Int,Int)]): Unit ={
    for(move <- possibleMoves ){
      fields(move._1)(move._2).background = fields(move._1)(move._2).color
    }
  }



}
