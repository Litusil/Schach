import model.{Bishop, ChessBoardFactory}
import org.specs2.mutable._


class BishopSpec extends Specification{


  "A Bishop" should{
    var r = new Bishop(true)
    var chessBoard = new ChessBoardFactory().create(3)
    "not be on chessboard" in {
      r.getPosition(chessBoard) must be_== ((-1,-1))
    }
  }

  "A Bishop" should{
    var r = new Bishop(true)
    var chessBoard = new ChessBoardFactory().create(3)
    chessBoard (1)(1) = r
    chessBoard (0)(0) = new Bishop(true)
    chessBoard (2)(2) = new Bishop(false)
    var moveableFields: Vector[(Int,Int)] = Vector();
    moveableFields :+ (0,2)
    moveableFields :+ (2,2)
    moveableFields :+ (2,0)
    "have possible moves on chessboard" in {
      r.getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }

  "A white model.Bishop" should {
    val r = new Bishop(true)
    "have toString() that is \u2657" in {
      r.toString must be_== ("\u2657")
    }
  }

  "A black model.Bishop" should {
    val r = new Bishop(false)
    "have toString that is \u265D" in {
      r.toString must be_== ("\u265D")
    }
  }
}