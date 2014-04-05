package no.bekk.rxscala.assignments

import org.scalatest.FlatSpec
import scala.concurrent._
import scala.util.{Success, Failure, Try}
import scala.collection.mutable
import scala.concurrent.duration._
import ExecutionContext.Implicits.global

class ObservableTimerSpec extends FlatSpec {

  it should "create an observable with a timer that emits a new int every 100ms and buffers in blocks of 5" in {
      val observable = ObservableTimer.createObservableTimer.take(3)

      val f = future(observable.toBlockingObservable.toList)

      Try(Await.result(f, 1600 millisecond)) match {
        case Failure(e) => fail("We don't have all day!")
        case Success(result) => {
          assert(result == List(
            mutable.Buffer(0,1,2,3,4),
            mutable.Buffer(5,6,7,8,9),
            mutable.Buffer(10,11,12,13,14)
          ))
        }
      }
    }

}
