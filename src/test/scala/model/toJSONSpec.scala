package model

import model.fileIOComponent.fileIoJsonImpl.FileIO
import org.specs2.mutable._

class toJSONSpec extends Specification{
  "A jsonmanager" should{
    var chessBoard = ChessBoard(Vector.fill(3,3)(None: Option[ChessPiece]))
    chessBoard = chessBoard.updatePlayer(false)
    chessBoard = chessBoard.putPiece(1,1,Option(Pawn(color = true,hasMoved = true)) )
    val json = new FileIO
    json.save(chessBoard)
    val result = json.load()

    "save and load correct" in {
      result must be_== (chessBoard)

    }
  }
}
