package cz.vlasec.gwi.csvimport

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import cz.vlasec.gwi.csvimport.Routes._

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object Main {
  def main(args: Array[String]): Unit = {

    implicit val system: ActorSystem[_] = ActorSystem(Behaviors.empty, "my-system")
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext: ExecutionContextExecutor = system.executionContext

    val bindingFuture = Http().newServerAt("localhost", 8080).bind(routes)

    println(s"Server now online. Please navigate to http://localhost:8080/task\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}

