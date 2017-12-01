import model.{ChessBoardFactory, Rook}
import org.specs2.mutable._

import scala.collection.immutable.Vector

class RookSpec extends Specification{

  "A Rook" should{
    var r = new Rook(true)
    var chessBoard = new ChessBoardFactory().create(3)
    "not be on chessboard" in {
      r.getPosition(chessBoard) must be_== ((-1,-1))
    }
  }

  "A Rook" should{
    var r = new Rook(true)
    var chessBoard = new ChessBoardFactory().create(3)
    chessBoard (1)(1) = r
    chessBoard (0)(1) = new Rook(true)
    chessBoard (1)(0) = new Rook(false)
    var moveableFields: Vector[(Int,Int)] = Vector();
    moveableFields :+ (1,0)
    moveableFields :+ (2,1)
    moveableFields :+ (1,2)
    "have possible moves on chessboard" in {
      r.getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }


  "A white model.Rook" should {
    val r = new Rook(true)
    "have toString() that is \u2656" in {
      r.toString must be_== ("\u2656")
    }
  }

  "A black model.Rook" should {
    val r = new Rook(false)
    "have toString that is \u265C" in {
      r.toString must be_== ("\u265C")
    }
  }



}