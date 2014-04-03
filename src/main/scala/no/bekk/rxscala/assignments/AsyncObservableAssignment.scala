package no.bekk.rxscala.assignments

import rx.lang.scala.Observable
import no.bekk.rxscala.util.Work._
import scala.concurrent.duration._
import scala.concurrent._
import ExecutionContext.Implicits.global

object AsyncObservableAssignment {

  // Create an Observable that emits the result of the two calculations.
  def mathCalculationsObservable(mathCalculation1: Calculation, mathCalculation2: Calculation): Observable[Int] = {
    Observable(subscriber => {
      val calculationFutures = List(mathCalculation1, mathCalculation2).map(calc => future(calc()))
      val results = calculationFutures.map(Await.result(_, 1000 milliseconds))
      results.foreach(subscriber.onNext)
      subscriber.onCompleted()
    })
  }

}
