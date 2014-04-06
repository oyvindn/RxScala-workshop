package no.bekk.rxscala.assignments

import rx.lang.scala.Observable
import no.bekk.rxscala.util.Work._
import scala.concurrent._
import ExecutionContext.Implicits.global

object AsyncObservable {

  /*
   * Create an Observable that emits the result of the two calculations.
   */
  def calculationsObservable(calculation1: Calculation, calculation2: Calculation): Observable[Int] = {
    def toAsyncObservable(calculation: Calculation) = Observable.from(future(calculation()))

    val obsCalc1 = toAsyncObservable(calculation1)
    val obsCalc2 = toAsyncObservable(calculation2)
    obsCalc1.merge(obsCalc2)
  }

}
