package no.bekk.rxscala.assignments

import org.scalatest.{Matchers, FlatSpec}
import scala.util.Random
import rx.lang.scala.Observable

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

  private def randomList(n: Int) = Seq.fill(n)(Random.nextInt).toList

}
