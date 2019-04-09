package model

import model.fileIOComponent.fileIoXmlImpl.FileIO
import org.specs2.mutable._

class toXMLSpec
  extends Specification{
  "A xmlmanager" should{
    var chessBoard = ChessBoard(Vector.fill(3,3)(None: Option[ChessPiece]))
    chessBoard.updatePlayer(false)

    chessBoard(1)(1) = Option(Pawn(color = true,moved = true))
    val xml = new FileIO
    xml.save(chessBoard,player)
    val result = xml.load()

    "save and load correct" in {
      result._1 must be_== (chessBoard)
      result._2 must be_== (player)
    }
  }
}
