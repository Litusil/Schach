package model

import org.specs2.mutable._

class toXMLSpec extends Specification{
  "A xmlmanager" should{
    var chessBoard = new ChessBoardFactory().create(3)
    val player = false
    chessBoard(1)(1) = new Pawn(true,true)
    val xml = new toXML
    xml.save(chessBoard,player)
    var result = xml.load()

    "save and load correct" in {
      result._1 must be_== (chessBoard)
      result._2 must be_== (player)
    }
  }
}
