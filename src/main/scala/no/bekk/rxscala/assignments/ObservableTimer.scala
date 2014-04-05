package no.bekk.rxscala.assignments

import scala.concurrent.duration._

import rx.lang.scala.Observable

class ObservableTimer {

  def createObservableTimer: Observable[Seq[Long]] = {
    Observable.timer(0 millis, 100 millis).buffer(500 millis).take(3)

  }

}
