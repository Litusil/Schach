package model

import model.fileIOComponent.fileIoXmlImpl.FileIO
import org.specs2.mutable._

class toXMLSpec
  extends Specification{
  "A xmlmanager" should{
    val chessBoard = new ChessBoardFactory().create(3)
    val player = false
    chessBoard(1)(1) = Pawn(color = true,moved = true)
    val xml = new FileIO
    xml.save(chessBoard,player)
    val result = xml.load()

    "save and load correct" in {
      result._1 must be_== (chessBoard)
      result._2 must be_== (player)
    }
  }
}
