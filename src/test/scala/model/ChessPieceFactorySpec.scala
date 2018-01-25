package model

import org.specs2.mutable.Specification

class ChessPieceFactorySpec extends Specification{

  var pieceFactory = new ChessPieceFactory


  "A ChessPieceFactory" should{
    val piece = Pawn(color = false,moved = false)
    val expect = pieceFactory.create("♟", hasMoved = false)
    "produce " in {
      piece must be_== (expect)
    }
  }

  "A ChessPieceFactory" should{
    val piece = Pawn(color = true,moved = false)
    val expect = pieceFactory.create("♙", hasMoved = false)
    "produce " in {
      piece must be_== (expect)
    }
  }

  "A ChessPieceFactory" should{
    val piece = King(color = false,moved = false)
    val expect = pieceFactory.create("♚", hasMoved = false)
    "produce " in {
      piece must be_== (expect)
    }
  }

  "A ChessPieceFactory" should{
    val piece = King(color = true,moved = false)
    val expect = pieceFactory.create("♔", hasMoved = false)
    "produce " in {
      piece must be_== (expect)
    }
  }

  "A ChessPieceFactory" should{
    val piece = Queen(color = false,moved = false)
    val expect = pieceFactory.create("♛", hasMoved = false)
    "produce " in {
      piece must be_== (expect)
    }
  }


  "A ChessPieceFactory" should{
    val piece = Queen(color = true,moved = false)
    val expect = pieceFactory.create("♕", hasMoved = false)
    "produce " in {
      piece must be_== (expect)
    }
  }

  "A ChessPieceFactory" should{
    val piece = Bishop(color = false,moved = false)
    val expect = pieceFactory.create("♝", hasMoved = false)
    "produce " in {
      piece must be_== (expect)
    }
  }


  "A ChessPieceFactory" should{
    val piece = Bishop(color = true,moved = false)
    val expect = pieceFactory.create("♗", hasMoved = false)
    "produce " in {
      piece must be_== (expect)
    }
  }

  "A ChessPieceFactory" should{
    val piece = Knight(color = false,moved = false)
    val expect = pieceFactory.create("♞", hasMoved = false)
    "produce " in {
      piece must be_== (expect)
    }
  }

  "A ChessPieceFactory" should{
    val piece = Knight(color = true,moved = false)
    val expect = pieceFactory.create("♘", hasMoved = false)
    "produce " in {
      piece must be_== (expect)
    }
  }


  "A ChessPieceFactory" should{
    val piece = Rook(color = false,moved = false)
    val expect = pieceFactory.create("♜", hasMoved = false)
    "produce " in {
      piece must be_== (expect)
    }
  }


  "A ChessPieceFactory" should{
    val piece = Rook(color = true,moved = false)
    val expect = pieceFactory.create("♖", hasMoved = false)
    "produce " in {
      piece must be_== (expect)
    }
  }



}
