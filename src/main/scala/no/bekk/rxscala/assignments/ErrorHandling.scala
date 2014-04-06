package no.bekk.rxscala.assignments

import rx.lang.scala.Observable
import scala.concurrent.duration._
import java.io.IOException
import no.bekk.rxscala.util.Connection


object ErrorHandling {

  /*
   * Create an observable that pings the given connection once each 100 millisecond.
   * If the ping returns true, the observable should emit "Success", or send out an IOException if the ping is false
   */
  def connectionWatch(connection: Connection): Observable[String] = {
    Observable[String](subscriber => {
      Observable.interval(100 milliseconds).subscribe(_ => {
        if(connection.ping) {
          subscriber.onNext("Success")
        } else {
          subscriber.onError(new IOException("Connection lost"))
        }
      })
    })
  }
}

