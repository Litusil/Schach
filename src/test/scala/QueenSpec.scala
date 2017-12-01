import model.{ChessBoardFactory, Queen}
import org.specs2.mutable._

class QueenSpec extends Specification{


  "A Queen" should{
    var queen = new Queen(true)
    var chessBoard = new ChessBoardFactory().create(3)
    "not be on chessboard" in {
      queen.getPosition(chessBoard) must be_== ((-1,-1))
    }
  }

  "A Queen" should{
    var queen = new Queen(true)
    var chessBoard = new ChessBoardFactory().create(2)
    chessBoard (1)(1) = queen
    chessBoard (0)(1) = new Queen(true)
    chessBoard (1)(0) = new Queen(true)
    chessBoard (0)(0) = new Queen(true)
    var moveableFields: Vector[(Int,Int)] = Vector();
    "has no possible moves on chessboard" in {
      queen.getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }

  "A Queen" should{
    var queen = new Queen(true)
    var chessBoard = new ChessBoardFactory().create(3)
    chessBoard (1)(1) = queen
    chessBoard (0)(1) = new Queen(true)
    chessBoard (1)(0) = new Queen(false)
    var moveableFields: Vector[(Int,Int)] = Vector();
    moveableFields :+ (1,0)
    moveableFields :+ (2,1)
    moveableFields :+ (1,2)
    moveableFields :+ (1,2)
    moveableFields :+ (0,2)
    moveableFields :+ (2,2)
    moveableFields :+ (0,0)
    "have possible moves on chessboard" in {
      queen.getPossibleMoves(chessBoard) must be_== (moveableFields)
    }
  }


  "A white model.Queen" should {
    val r = new Queen(true)
    "have toString() that is \u2655" in {
      r.toString must be_== ("\u2655")
    }


  }

  "A black model.Queen" should {
    val r = new Queen(false)
    "have toString that is \u265C" in {
      r.toString must be_== ("\u265B")
    }
  }



}

