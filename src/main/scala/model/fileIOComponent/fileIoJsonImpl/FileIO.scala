package model.fileIOComponent.fileIoJsonImpl

import model.fileIOComponent.FileIOInterface
import model.{ChessBoard, ChessBoardFactory, ChessPiece, ChessPieceFactory}
import play.api.libs.json._

import scala.collection.immutable.Vector
import scala.io.Source

class FileIO extends FileIOInterface {
  override def save(cb: ChessBoard, fileName: String): Unit = {
    import java.io._
    val pw = new PrintWriter(new File(fileName))
    pw.write(Json.prettyPrint(gridToJson(cb)))
    pw.close()
  }

  def gridToJson(cb: ChessBoard): JsObject = {

    var pieces: Vector[((Int, Int), Boolean, String)] = Vector()

    for (piece <- cb.whitePieces) {
      pieces = pieces :+ (piece.position, piece.hasMoved, piece.toString)
    }
    for (piece <- cb.whitePiecesTaken) {
      pieces = pieces :+ (piece.position, piece.hasMoved, piece.toString)
    }
    for (piece <- cb.blackPieces) {
      pieces = pieces :+ (piece.position, piece.hasMoved, piece.toString)
    }
    for (piece <- cb.blackPiecesTaken) {
      pieces = pieces :+ (piece.position, piece.hasMoved, piece.toString)
    }

    Json.obj(
      "grid" -> Json.obj(
        "size" -> JsNumber(cb.boardSize),
        "player" -> JsBoolean(cb.currentPlayer),
        "whiteCheck" -> JsBoolean(cb.whiteCheck),
        "blackCheck" -> JsBoolean(cb.blackCheck),
        "cells" -> Json.toJson(
          for {
            p <- pieces
          } yield {
            Json.obj(
              "posY" -> p._1._1,
              "posX" -> p._1._2,
              "hasMoved" -> p._2,
              "piece" -> p._3
            )
          }
        )
      )
    )
  }

  override def load(name: String): (ChessBoard) = {
    val pieceFactory = new ChessPieceFactory
    val boardFactory = new ChessBoardFactory()
    val source: String = Source.fromFile(name).getLines.mkString
    val json: JsValue = Json.parse(source)
    val cb = new ChessBoard()

    var whitePieces: Vector[(ChessPiece)] = Vector()
    var blackPieces: Vector[(ChessPiece)] = Vector()
    var whitePiecesTaken: Vector[(ChessPiece)] = Vector()
    var blackPiecesTaken: Vector[(ChessPiece)] = Vector()

    val size = (json \ "grid" \ "size").get.toString.toInt
    var board: Array[Array[ChessPiece]] = boardFactory.create(size)

    val currentPlayer = (json \ "grid" \ "player").get.toString.toBoolean
    val whiteCheck = (json \ "grid" \ "whiteCheck").get.toString.toBoolean
    val blackCheck = (json \ "grid" \ "blackCheck").get.toString.toBoolean

    val cells = (json \ "grid" \ "cells").as[List[JsObject]]

    for (c <- cells) {
      val posY = (c \ "posY").get.toString().toInt
      val posX = (c \ "posX").get.toString().toInt
      val hasMoved = (c \ "hasMoved").get.toString().toBoolean
      val pieceString = (c \ "piece").get.toString().replace("\"", "").trim

      val piece = pieceFactory.create(pieceString, hasMoved, (posY, posX))
      if (posY >= 0 && posX >= 0) {
        board(posY)(posX) = piece
        if (piece.color) {
          whitePieces = whitePieces :+ piece
        } else {
          blackPieces = blackPieces :+ piece
        }
      } else {
        if (piece.color) {
          whitePiecesTaken = whitePiecesTaken :+ piece
        } else {
          blackPiecesTaken = blackPiecesTaken :+ piece
        }
      }
    }

    cb.board = board
    cb.boardSize = size
    cb.currentPlayer = currentPlayer
    cb.whiteCheck = whiteCheck
    cb.blackCheck = blackCheck
    cb.whitePieces = whitePieces
    cb.blackPieces = blackPieces
    cb.whitePiecesTaken = whitePiecesTaken
    cb.blackPiecesTaken = blackPiecesTaken

    cb
  }
}


