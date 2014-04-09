package no.bekk.rxscala.examples

import rx.lang.scala.Observable
import no.bekk.rxscala.util.ThreadRunner

object ErrorHandling extends App {
  def customNonBlockingObservableWithErrorHandling(): Observable[String] = {
    Observable(subscriber => {
      // For simplicity this example uses a Thread instead of an ExecutorService/ThreadPool
      ThreadRunner(() => {
        0 to 50 foreach (n => {
          if (!subscriber.isUnsubscribed) {
            if(n < 30) {
              subscriber.onNext(s"value $n")
            } else {
              subscriber.onError(new RuntimeException("I am tired, do it yourself!"))
            }
          }
        })

        // after sending all values we complete the sequence
        subscriber.onCompleted()
      })
    })
  }

  customNonBlockingObservableWithErrorHandling().subscribe(next => println(next), error => println(s"error: ${error.getMessage}"))
}
