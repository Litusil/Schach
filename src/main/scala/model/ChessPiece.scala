package model

abstract class ChessPiece {
    var hasMoved = false;

    def getPossibleMoves(chessBoard: Array[ChessPiece])

}
