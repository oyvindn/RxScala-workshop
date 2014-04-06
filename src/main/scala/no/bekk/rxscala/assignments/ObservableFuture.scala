package no.bekk.rxscala.assignments

import scala.concurrent.{ExecutionContext, Future}
import rx.lang.scala.Observable
import ExecutionContext.Implicits.global

object ObservableFuture {

  /*
   * Create an observable emitting the value produced by the Future as its single item.
   */
  def createObservableFuture(f: Future[List[String]]): Observable[List[String]] = {
    Observable.from(f)
  }

}
