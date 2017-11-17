import model.{Rook,ChessBoardFactory}
import org.specs2.mutable._

class RookSpec extends Specification{



  "A Rook" should{
    var r = new Rook(true)
    var chessBoard = new ChessBoardFactory().create(3)
    chessBoard (1)(1) = r;
    "be on the chessBoard" in {
      r.isOnChessBoard(chessBoard) must be_== (true)
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