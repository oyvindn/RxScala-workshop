package no.bekk.rxscala.assignments

import rx.lang.scala.Observable
import no.bekk.rxscala.util.Work._
import scala.concurrent.duration._
import scala.concurrent._
import ExecutionContext.Implicits.global

object AsyncObservables {

  /*
   * Create an Observable that emits the result of the two calculations.
   */
  def calculationsObservable(calculation1: Calculation, calculation2: Calculation): Observable[Int] = {
    def toAsyncObservable(calculation: Calculation) = Observable.from(future(calculation()))

    val obsCalc1 = toAsyncObservable(calculation1)
    val obsCalc2 = toAsyncObservable(calculation2)
    obsCalc1.merge(obsCalc2)
  }



  /*
   * Create an observable emitting the value produced by the Future as its single item.
   */
  def observableFuture(future: Future[List[String]]): Observable[List[String]] = Observable.from(future)



  /*
   * Create an observable using a timer or interval that emits a new Int every 100ms, and transform it to emit buffers of 5 values at a time
   */
  def observableTimer: Observable[Seq[Long]] = Observable.interval(100 millisecond).buffer(5)

}
