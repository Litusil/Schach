package model

import model.fileIOComponent.fileIoJsonImpl.FileIO
import org.specs2.mutable._

class toJSONSpec extends Specification{
  "A jsonmanager" should{
    val chessBoard = new ChessBoardFactory().create(3)
    val player = false
    chessBoard(1)(1) = Pawn(color = true, moved = true)
    val json = new FileIO
    json.save(chessBoard,player)
    val result = json.load()

    "save and load correct" in {
      result._1 must be_== (chessBoard)
      result._2 must be_== (player)
    }
  }
}
