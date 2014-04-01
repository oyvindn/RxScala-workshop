package no.bekk.rxscala.examples

import no.bekk.rxscala.util.ThreadRunner
import rx.lang.scala.Observable

/*
 * You can implement asynchronous IO, computational operations, or “infinite” streams of data by using
 * the Observable class.
 */

/*
 * Observable that blocks when subscribed to (does not spawn an extra thread)
 */
object ImplementingAnSynchronousObservable extends App {
  def customBlockingObservable(): Observable[String] = {
    Observable(subscriber => {
      0 to 50 foreach (n => if (!subscriber.isUnsubscribed) subscriber.onNext(s"value $n"))

      // after sending all values we complete the sequence
      subscriber.onCompleted()
    })
  }

  val observable = customBlockingObservable()
  observable.subscribe(s => println(s))
  //  observable.subscribe(s => println(s))
}


/*
 * Observable that does not block when subscribed to as it spawns a separate thread.
 */
object ImplementingAnAsynchronousObservable extends App {
  def customNonBlockingObservable(): Observable[String] = {
    Observable(subscriber => {
      // For simplicity this example uses a Thread instead of an ExecutorService/ThreadPool
      ThreadRunner {
        0 to 50 foreach (n => if (!subscriber.isUnsubscribed) subscriber.onNext(s"value $n"))

        // after sending all values we complete the sequence
        subscriber.onCompleted()
      }
    })
  }

  val observable = customNonBlockingObservable()
  observable.subscribe(s => println(s))
  //  observable.subscribe(s => println(s))
}


