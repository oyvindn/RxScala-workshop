package no.bekk.rxscala.assignments

import org.scalatest.{Matchers, FlatSpec}
import no.bekk.rxscala.util.Connection
import java.io.IOException
import scala.concurrent.duration._

class ErrorHandlingSpec extends FlatSpec with Matchers {

  it should "emit 10 successes and 1 IOException" in {
    val res = ErrorHandling.connectionWatch(Connection())
      .onErrorReturn(e => if(e.isInstanceOf[IOException]) "IOException" else "Wrong Exception")
      .toBlockingObservable
      .toList
    assert(res.size === 11)
    res.take(10).foreach(s => assert(s === "Success"))
    assert(res.last === "IOException")
  }

  it should "emit success each 100 milliseconds" in {
    val res = ErrorHandling.connectionWatch(Connection())
      .debounce(90 milliseconds)
      .onErrorReturn(e => if(e.isInstanceOf[IOException]) "IOException" else "Wrong Exception")
      .toBlockingObservable
      .toList
    assert(res.size === 11)
    res.take(10).foreach(s => assert(s === "Success"))
    assert(res.last === "IOException")
  }
}
