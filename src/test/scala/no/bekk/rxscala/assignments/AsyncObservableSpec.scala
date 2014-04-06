package no.bekk.rxscala.assignments

import org.scalatest._
import no.bekk.rxscala.util.Work._
import scala.concurrent.duration._
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}

class AsyncObservableSpec extends FlatSpec with Matchers {

  it should "emit the result of the two calculation" in {
    val res1 = 64
    val res2 = 1024
    val calc1: Calculation = () => work(500 millisecond, res1)
    val calc2: Calculation = () => work(500 millisecond, res2)
    val observable = AsyncObservable.calculationsObservable(calc1, calc2)

    val f = future(observable.toBlockingObservable.toList)

    Try(Await.result(f, 600 millisecond)) match {
      case Failure(e) => fail("We don't have all day!")
      case Success(result) =>
        assert(result.length === 2)
        result should contain allOf (res1, res2)
    }
  }

}
