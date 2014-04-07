package no.bekk.rxscala.assignments

import org.scalatest.{Matchers, FlatSpec}
import scala.util.{Success, Failure, Try, Random}
import rx.lang.scala.Observable
import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global

class BasicObservablesSpec extends FlatSpec with Matchers {

  it should "create an observable that emits each of the elements in the list multiplied with 2" in {
    val list = randomList(100)
    val result = BasicObservables.observableFromList(list).toBlockingObservable.toList
    assert(result.size === list.size)
    result should contain theSameElementsInOrderAs (list.map(_ * 2))
  }

  it should "create an observable that for each time there is a value from both lists, emits the sum of the who values" in {
    val list1 = randomList(100)
    val list2 = randomList(100)
    val result = BasicObservables.observableSums(Observable.from(list1), Observable.from(list2)).toBlockingObservable.toList
    assert(result.size === list1.size)
    result should contain theSameElementsInOrderAs (list1.zip(list2).map { case (x,y) => x + y})
  }

  it should "create an observable that emits the sum of the 10 last positive numbers emitted by the given observable" in {
    val list = randomList(100)
    val result = BasicObservables.observableSumLastTen(Observable.from(list)).toBlockingObservable.single
    assert(result === list.filter(_ > 0).takeRight(10).sum)
  }

  it should "create an observable emitting the value produced by the Future as its single item" in {
    val f: Future[List[String]] = future {
      List("Camping", "filming", "test")
    }

    val result = BasicObservables.observableFuture(f).toBlockingObservable.toList
    assert(result.size === 1)
    assert(result.head === List("Camping", "filming", "test"))
  }

  it should "create an observable with a timer that emits a new int every 100ms and buffers it in blocks of 5" in {
    val observable = BasicObservables.observableTimer.debounce(100 millisecond).take(3)

    val f = future(observable.toBlockingObservable.toList)
    Try(Await.result(f, 1600 millisecond)) match {
      case Failure(e) => fail("We don't have all day!")
      case Success(result) => {
        result should contain theSameElementsInOrderAs (List(List(0,1,2,3,4), List(5,6,7,8,9), List(10,11,12,13,14)))
      }
    }
  }

  private def randomList(n: Int) = Seq.fill(n)(Random.nextInt).toList

}
