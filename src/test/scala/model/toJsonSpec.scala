package model

import org.specs2.mutable._

class toJsonSpec extends Specification{
  "A jsonmanager" should{
    var chessBoard = new ChessBoardFactory().create(3)
    val player = false
    chessBoard(1)(1) = new Pawn(true,true)
    val json = new toJson
    json.save(chessBoard,player)
    var result = json.load()

    "save and load correct" in {
      result._1 must be_== (chessBoard)
      result._2 must be_== (player)
    }
  }
}
