
package view


import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.Done
import akka.http.scaladsl.server.{ExceptionHandler, Route}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import controller.ChessController
import model.ChessBoard
import util.Observer

import scala.util.{Failure, Success}
// for JSON serialization/deserialization following dependency is required:
// "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.7"
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._

import scala.io.StdIn

import scala.concurrent.Future


case class WebServer (controller: ChessController) {

  object TestException extends Throwable

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  implicit val executionContext = system.dispatcher

  val apiRoutes: Route =
      get {
        path("board"){
          complete {
            controller.chessBoard.toString
          }
        }
      } ~
      get {
        path("move") { s =>
          val move = processInputLine(s.toString)
          move.onComplete{
            case Success(board) => {

            }
            case Failure(e) => println("move failed")
          }
          complete{controller.chessBoard.toString}
        }


  def processInputLine(eingabe: String): Future[String] ={
    val f = Future{
      var command = eingabe.trim().toUpperCase
      if (eingabe.trim().toUpperCase.matches("[a-hA-H][1-8]( |-)[a-hA-H][1-8]")){
        command = command.replaceAll("A","0")
        command = command.replaceAll("B","1")
        command = command.replaceAll("C","2")
        command = command.replaceAll("D","3")
        command = command.replaceAll("E","4")
        command = command.replaceAll("F","5")
        command = command.replaceAll("G","6")
        command = command.replaceAll("H","7")
      }else {
        throw new Exception("Illegal Move")
      }
      command
    }
    f
  }


  val bindingFuture = Http().bindAndHandle(apiRoutes, "localhost", 8080)
  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ ⇒ system.terminate()) // and shutdown when done


}