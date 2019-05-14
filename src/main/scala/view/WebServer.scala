
package view


import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.{ExceptionHandler, Route}
import akka.http.scaladsl.server.Directives._
import controller.ChessController



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
        path("move" / Segment) { s =>
          get {
            complete {
              processInputLine(s)
            }
          }
        }

  def processInputLine(eingabe: String): String = {
      if (eingabe.trim().toUpperCase.matches("[a-hA-H][1-8]( |-)[a-hA-H][1-8]")){
        var command = eingabe.trim().toUpperCase
        command = command.replaceAll("A","0")
        command = command.replaceAll("B","1")
        command = command.replaceAll("C","2")
        command = command.replaceAll("D","3")
        command = command.replaceAll("E","4")
        command = command.replaceAll("F","5")
        command = command.replaceAll("G","6")
        command = command.replaceAll("H","7")
        controller.move(command.charAt(0)-48,command.charAt(1)-49,command.charAt(3)-48,command.charAt(4)-49)
        Thread.sleep(250)
        controller.chessBoard.toString
      }else {
        throw new Exception("Illegal Move")
      }
    }


  val bindingFuture = Http().bindAndHandle(apiRoutes, "localhost", 8080)
  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")

  /*
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ ⇒ system.terminate()) // and shutdown when done
 */

}