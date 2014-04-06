package no.bekk.rxscala.assignments

import org.scalatest.{Matchers, FlatSpec}
import scala.util.Random

class BasicObservablesSpec extends FlatSpec with Matchers {

  it should "create an observable that emits each of the elements in the list multiplied with 2" in {
    val list = Seq.fill(100)(Random.nextInt).toList
    val result = BasicObservables.observableFromList(list).toBlockingObservable.toList
    assert(result.size === list.size)
    result should contain theSameElementsAs (list.map(_ * 2))
  }

}
