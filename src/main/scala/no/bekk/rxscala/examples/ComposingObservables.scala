package no.bekk.rxscala.examples

import ImplementingAnAsynchronousObservableWithFuture._

/*
 * You can chain operators together to transform and compose Observables
 */
object ComposingObservables extends App {
  customNonBlockingObservable().drop(10).take(5).map(s => s"new $s").subscribe(s => println(s))

  Thread.sleep(1000);
}
