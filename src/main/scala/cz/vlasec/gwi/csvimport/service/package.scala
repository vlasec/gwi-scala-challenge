package cz.vlasec.gwi.csvimport

package object service {
  import akka.actor.typed.ActorRef
  import cz.vlasec.gwi.csvimport.service.CsvService.CsvServiceCommand
  import cz.vlasec.gwi.csvimport.service.CsvTask.CsvTaskCommand
  import cz.vlasec.gwi.csvimport.service.CsvWorker.CsvWorkerCommand

  type JsonUrl = String
  type CsvUrl = String
  type TaskId = Long
  private[service] type TaskRef = ActorRef[CsvTaskCommand]
  private[service] type WorkerRef = ActorRef[CsvWorkerCommand]
  private[service] type ServiceRef = ActorRef[CsvServiceCommand]
}