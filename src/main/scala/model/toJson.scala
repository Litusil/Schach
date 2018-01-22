package model

import play.api.libs.json._

import scala.collection.immutable.Vector
import scala.io.Source

class toJson extends fileIOInterface {
  override def save(board: Array[Array[ChessPiece]],player: Boolean): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("board.json"))
    pw.write(Json.prettyPrint(gridToJson(board,player)))
    pw.close
  }

  def gridToJson(board: Array[Array[ChessPiece]],player: Boolean) = {

    var pieces: Vector[(Int,Int,Boolean,String)] = Vector()

    for (y <- 0 to board.length - 1) {
      for (x <- 0 to board.length - 1) {
        if (board(y)(x) != null) {
          pieces = pieces :+ (y,x,board(y)(x).hasMoved,board(y)(x).toString())
        }
      }
    }

    Json.obj(
      "grid" -> Json.obj(
        "size" -> JsNumber(board.size),
        "player" -> JsBoolean(player),
        "cells" -> Json.toJson(
          for {
            p <- pieces
          } yield {
            Json.obj(
              "row" -> p._1,
              "col" -> p._2,
              "hasMoved" -> p._3,
              "piece" -> p._4.toString
            )
            }
        )
      )
    )
  }


  override def load: (Array[Array[ChessPiece]],Boolean) = {

    var PieceFactory = new ChessPieceFactory
    val source: String = Source.fromFile("board.json").getLines.mkString
    val json: JsValue = Json.parse(source)

    val size = (json \ "grid" \ "size").get.toString.toInt
    var chessBoard = new ChessBoardFactory().create(size)
    var currentPlayer = (json \ "grid" \ "player").get.toString.toBoolean

    val cells = (json \ "grid" \ "cells").as[List[JsObject]]

    for (c <- cells){
      val row = (c \ "row").get.toString().toInt
      val col = (c \ "col").get.toString().toInt
      val hasMoved = (c \ "hasMoved").get.toString().toBoolean
      val piece = (c \ "piece").get.toString().replace("\"","").trim
      chessBoard(row)(col) = PieceFactory.create(piece,hasMoved)
    }

    return (chessBoard,currentPlayer)
  }





}



