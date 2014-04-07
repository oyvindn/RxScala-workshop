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
    val observable = AsyncObservables.calculationsObservable(calc1, calc2)

    val f = future(observable.toBlockingObservable.toList)

    Try(Await.result(f, 600 millisecond)) match {
      case Failure(e) => fail("We don't have all day!")
      case Success(result) =>
        assert(result.length === 2)
        result should contain allOf (res1, res2)
    }
  }

  it should "create an observable emitting the value produced by the Future as its single item" in {
    val f: Future[List[String]] = future {
      List("Camping", "filming", "test")
    }

    val result = AsyncObservables.observableFuture(f).toBlockingObservable.toList
    assert(result.size === 1)
    assert(result.head === List("Camping", "filming", "test"))
  }

  it should "create an observable with a timer that emits a new int every 100ms and buffers it in blocks of 5" in {
    val observable = AsyncObservables.observableTimer.take(3)

    val f = future(observable.toBlockingObservable.toList)
    Try(Await.result(f, 1600 millisecond)) match {
      case Failure(e) => fail("We don't have all day!")
      case Success(result) => result should contain theSameElementsInOrderAs List(List(0,1,2,3,4), List(5,6,7,8,9), List(10,11,12,13,14))
    }
  }

}
