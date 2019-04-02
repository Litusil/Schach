package model

import org.specs2.mutable.Specification

class ChessPieceFactorySpec extends Specification{

  var pieceFactory = new ChessPieceFactory


  "A ChessPieceFactory" should{
    val piece = Option(Pawn(color = false,moved = false))
    val expect = pieceFactory.create("♟", hasMoved = false)
    "produce " in {
      piece must be_== (expect)
    }
  }

  "A ChessPieceFactory" should{
    val piece = Option(Pawn(color = true,moved = false))
    val expect = pieceFactory.create("♙", hasMoved = false)
    "produce " in {
      piece must be_== (expect)
    }
  }

  "A ChessPieceFactory" should{
    val piece = Option(King(color = false,moved = false))
    val expect = pieceFactory.create("♚", hasMoved = false)
    "produce " in {
      piece must be_== (expect)
    }
  }

  "A ChessPieceFactory" should{
    val piece = Option(King(color = true,moved = false))
    val expect = pieceFactory.create("♔", hasMoved = false)
    "produce " in {
      piece must be_== (expect)
    }
  }

  "A ChessPieceFactory" should{
    val piece = Option(Queen(color = false,moved = false))
    val expect = pieceFactory.create("♛", hasMoved = false)
    "produce " in {
      piece must be_== (expect)
    }
  }


  "A ChessPieceFactory" should{
    val piece = Option(Queen(color = true,moved = false))
    val expect = pieceFactory.create("♕", hasMoved = false)
    "produce " in {
      piece must be_== (expect)
    }
  }

  "A ChessPieceFactory" should{
    val piece = Option(Bishop(color = false,moved = false))
    val expect = pieceFactory.create("♝", hasMoved = false)
    "produce " in {
      piece must be_== (expect)
    }
  }


  "A ChessPieceFactory" should{
    val piece = Option(Bishop(color = true,moved = false))
    val expect = pieceFactory.create("♗", hasMoved = false)
    "produce " in {
      piece must be_== (expect)
    }
  }

  "A ChessPieceFactory" should{
    val piece = Option(Knight(color = false,moved = false))
    val expect = pieceFactory.create("♞", hasMoved = false)
    "produce " in {
      piece must be_== (expect)
    }
  }

  "A ChessPieceFactory" should{
    val piece = Option(Knight(color = true,moved = false))
    val expect = pieceFactory.create("♘", hasMoved = false)
    "produce " in {
      piece must be_== (expect)
    }
  }


  "A ChessPieceFactory" should{
    val piece = Option(Rook(color = false,moved = false))
    val expect = pieceFactory.create("♜", hasMoved = false)
    "produce " in {
      piece must be_== (expect)
    }
  }


  "A ChessPieceFactory" should{
    val piece = Option(Rook(color = true,moved = false))
    val expect = pieceFactory.create("♖", hasMoved = false)
    "produce " in {
      piece must be_== (expect)
    }
  }



}
