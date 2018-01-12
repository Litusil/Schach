package view.swing

import scala.swing._
import java.awt.Color

import controller.ChessController
import util.Observer

class Gui(controller: ChessController) extends MainFrame with Observer {

    controller.add(this)
    title = "Schach"
    preferredSize = new Dimension(750, 750)
    //controller.add(this)


    var fields = Array.ofDim[Field](8,8)
    for(i <- 0 to controller.chessBoard.size-1){
        for(j <- 0 to controller.chessBoard.size-1) {

            if (((i + 1) % 2 )==1){
                if(((j+1) % 2) == 1){
                    if (controller.chessBoard(i)(j) != null) {
                        fields(i)(j) = new Field(controller.chessBoard(i)(j), Color.LIGHT_GRAY, controller, this)
                    }else {
                        fields(i)(j) = new Field(null, Color.LIGHT_GRAY, controller, this)
                    }
                }else {
                    if (controller.chessBoard(i)(j) != null) {
                        fields(i)(j) = new Field(controller.chessBoard(i)(j), Color.WHITE, controller, this)
                    }else {
                        fields(i)(j) = new Field(null, Color.WHITE, controller, this)
                    }
                }
            }else{
                if(((j+1) % 2) == 1){
                    if (controller.chessBoard(i)(j) != null){
                        fields(i)(j) = new Field(controller.chessBoard(i)(j), Color.WHITE, controller, this)
                    }else {
                        fields(i)(j) = new Field(null, Color.WHITE, controller, this)
                    }
                }else {
                    if (controller.chessBoard(i)(j) != null) {
                        fields(i)(j) = new Field(controller.chessBoard(i)(j), Color.LIGHT_GRAY, controller, this)
                    }else {
                        fields(i)(j) = new Field(null,Color.LIGHT_GRAY, controller, this)
                    }
                }
            }
        }
    }
    contents = new GridPanel(8,8){
        for(i <- 0 to fields.size-1){
            for(j <- 0 to fields.size-1) {
                if(fields(i)(j) != null){
                    contents += fields(i)(j)
                }
            }
        }
    }
    visible  = true

    def update(): Unit ={
        for (i <- 0 to controller.chessBoard.size-1){
            for (j <- 0 to controller.chessBoard.size-1){
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




}
