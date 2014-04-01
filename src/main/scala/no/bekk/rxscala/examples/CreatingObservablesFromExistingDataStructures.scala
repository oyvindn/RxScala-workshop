package no.bekk.rxscala.examples

import rx.lang.scala.Observable

/*
 * These converted Observables will synchronously invoke the onNext() method of any Subscriber that
 * subscribes to them, for each item emitted by the Observable, and will then invoke the Subscriber’s
 * onCompleted() method.
 */
object CreatingObservablesFromExistingDataStructures extends App {
  val numbers = List(1, 2, 3, 4, 5)
  val observable = Observable.from(numbers)

  observable subscribe(n => println(n))
}