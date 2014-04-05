package no.bekk.rxscala.assignments

import scala.concurrent.{ExecutionContext, Future}
import rx.lang.scala.Observable
import ExecutionContext.Implicits.global

class SimpleObservableFuture {

  def createObservableFuture(f: Future[List[String]]): Observable[List[String]] = {
    Observable.from(f)
  }

}
