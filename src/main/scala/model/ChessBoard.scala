package model

case class ChessBoard(size: Int) {

  var field: Vector[Vector[Option[ChessPiece]]] = Vector.fill(size,size)(None: Option[ChessPiece])

  def defaultInit(): Unit ={
    val PieceFactory = new ChessPieceFactory
    var pieces: Vector[Option[ChessPiece]] = Vector()

    pieces = (pieces
      :+ PieceFactory.create("♖",hasMoved = false)
      :+ PieceFactory.create("♘",hasMoved = false)
      :+ PieceFactory.create("♗",hasMoved = false)
      :+ PieceFactory.create("♕",hasMoved = false)
      :+ PieceFactory.create("♔",hasMoved = false)
      :+ PieceFactory.create("♗",hasMoved = false)
      :+ PieceFactory.create("♘",hasMoved = false)
      :+ PieceFactory.create("♖",hasMoved = false))

    field = field.updated(0,pieces)
    pieces = Vector()

    for(i <- 0 to 7){
      pieces = pieces :+ PieceFactory.create("♙",hasMoved = false)
    }

    field = field.updated(1,pieces)
    pieces = Vector()

    pieces = (pieces
      :+ PieceFactory.create("♜",hasMoved = false)
      :+ PieceFactory.create("♞",hasMoved = false)
      :+ PieceFactory.create("♝",hasMoved = false)
      :+ PieceFactory.create("♛",hasMoved = false)
      :+ PieceFactory.create("♚",hasMoved = false)
      :+ PieceFactory.create("♝",hasMoved = false)
      :+ PieceFactory.create("♞",hasMoved = false)
      :+ PieceFactory.create("♜",hasMoved = false))

    field = field.updated(7,pieces)
    pieces = Vector()

    for(i <- 0 to 7){
      pieces = pieces :+ PieceFactory.create("♟",hasMoved = false)
    }

    field = field.updated(6,pieces)


  }
}
