package model

import org.specs2.mutable.Specification
import model.ChessBoardFactory

class ChessPieceFactorySpec extends Specification{

  var pieceFactory = new ChessPieceFactory


  "A ChessPieceFactory" should{
    var piece = new Pawn(false,false)
    var expect = pieceFactory.create("♟", false)
    "produce " in {
      piece must be_== (expect)
    }
  }

  "A ChessPieceFactory" should{
    var piece = new Pawn(true,false)
    var expect = pieceFactory.create("♙", false)
    "produce " in {
      piece must be_== (expect)
    }
  }

  "A ChessPieceFactory" should{
    var piece = new King(false,false)
    var expect = pieceFactory.create("♚", false)
    "produce " in {
      piece must be_== (expect)
    }
  }

  "A ChessPieceFactory" should{
    var piece = new King(true,false)
    var expect = pieceFactory.create("♔", false)
    "produce " in {
      piece must be_== (expect)
    }
  }

  "A ChessPieceFactory" should{
    var piece = new Queen(false,false)
    var expect = pieceFactory.create("♛", false)
    "produce " in {
      piece must be_== (expect)
    }
  }


  "A ChessPieceFactory" should{
    var piece = new Queen(true,false)
    var expect = pieceFactory.create("♕", false)
    "produce " in {
      piece must be_== (expect)
    }
  }

  "A ChessPieceFactory" should{
    var piece = new Bishop(false,false)
    var expect = pieceFactory.create("♝", false)
    "produce " in {
      piece must be_== (expect)
    }
  }


  "A ChessPieceFactory" should{
    var piece = new Bishop(true,false)
    var expect = pieceFactory.create("♗", false)
    "produce " in {
      piece must be_== (expect)
    }
  }

  "A ChessPieceFactory" should{
    var piece = new Knight(false,false)
    var expect = pieceFactory.create("♞", false)
    "produce " in {
      piece must be_== (expect)
    }
  }

  "A ChessPieceFactory" should{
    var piece = new Knight(true,false)
    var expect = pieceFactory.create("♘", false)
    "produce " in {
      piece must be_== (expect)
    }
  }


  "A ChessPieceFactory" should{
    var piece = new Rook(false,false)
    var expect = pieceFactory.create("♜", false)
    "produce " in {
      piece must be_== (expect)
    }
  }


  "A ChessPieceFactory" should{
    var piece = new Rook(true,false)
    var expect = pieceFactory.create("♖", false)
    "produce " in {
      piece must be_== (expect)
    }
  }



}
