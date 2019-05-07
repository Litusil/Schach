
import java.util.concurrent.TimeUnit

import controller.ChessController
import view.{Tui, UiFactory, WebServer}
import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import view.UiMessage.{CreateGui, CreateTui}

import scala.concurrent.Await
import scala.concurrent.duration.{Duration, FiniteDuration}
import scala.io.StdIn.readLine

object Schach {
    val controller = new ChessController

    implicit val timeout: Timeout = Timeout(FiniteDuration(10, TimeUnit.SECONDS)) // used for akka ask pattern
    implicit val actorSystem = ActorSystem("actorSystem")
    val uiFactory = actorSystem.actorOf(Props[UiFactory])

    val tuiFuture = uiFactory ? CreateTui(controller)
    val tui = Await.result(tuiFuture.mapTo[Tui], Duration.Inf)

    uiFactory ! CreateGui(controller)

    val server = WebServer(controller)



  def main(args: Array[String]) {
        var input: String = ""

        do {
          input = readLine()
          tui.processInputLine(input)

        } while (input != "q")

  }
}

