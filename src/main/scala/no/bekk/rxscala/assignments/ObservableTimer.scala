package no.bekk.rxscala.assignments

import scala.concurrent.duration._
import rx.lang.scala.Observable

object ObservableTimer {

  /*
   * Create an observable using a timer that emits a new Int every 100ms, and transform it to emit buffers of 5 values at a time
   */
  def createObservableTimer: Observable[Seq[Long]] = {
    Observable.timer(0 millis, 100 millis).buffer(500 millis)

  }

}
