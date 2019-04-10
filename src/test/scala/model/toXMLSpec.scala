package model

import model.fileIOComponent.fileIoXmlImpl.FileIO
import org.specs2.mutable._

class toXMLSpec
  extends Specification{
  "A xmlmanager" should{
    var chessBoard = ChessBoard(Vector.fill(3,3)(None: Option[ChessPiece]))
    chessBoard = chessBoard.updatePlayer(false)
    chessBoard = chessBoard.putPiece(1,1,Option(Pawn(color = true,hasMoved = true)) )

    val xml = new FileIO
    xml.save(chessBoard)
    val result = xml.load()

    "save and load correct" in {
      result must be_== (chessBoard)
    }
  }
}
