package controller
import com.google.inject.Guice
import net.codingwell.scalaguice.InjectorExtensions._
import model._
import model.fileIOComponent.FileIOInterface
import util.Observable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}



class ChessController extends Observable {


  val injector = Guice.createInjector(new SchachModule)
  val slmanager = injector.instance[FileIOInterface]
  val boardSize = 8
  var chessBoard: ChessBoard = newGame()

  def save(): Unit = {
    slmanager.save(chessBoard)
  }

  def load():Unit = {
    chessBoard = slmanager.load()
    notifyObservers()
  }

  def newGame(): ChessBoard = {
    new ChessBoard(Vector.fill(boardSize,boardSize)(None: Option[ChessPiece])).defaultInit()
  }

  def move(x_start: Int,y_start: Int,x_ziel: Int,y_ziel: Int): Unit = {

    val future1 = Future(chessBoard.move(x_start: Int, y_start: Int, x_ziel: Int, y_ziel: Int))

    future1.onComplete{
      case Success(board) => {
       board match {
          case Some(x: ChessBoard) => {
            chessBoard = x
            notifyObservers()
          }
          case None => {
            println("ungültiger Zug!")
            notifyObservers()
          }
       }
      }
      case Failure(e) => println("move failed")
    }
  }
}
