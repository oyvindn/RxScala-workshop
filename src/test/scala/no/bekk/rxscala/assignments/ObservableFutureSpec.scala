package no.bekk.rxscala.assignments

import org.scalatest.FlatSpec
import scala.concurrent._
import ExecutionContext.Implicits.global

class ObservableFutureSpec extends FlatSpec {


  it should "create an observable emitting the value produced by the Future as its single item" in {
    val f: Future[List[String]] = future {
      List("Camping", "filming", "test")
    }

   val result = ObservableFuture.createObservableFuture(f).toBlockingObservable.toList
   assert(result.size === 1)
   assert(result.head === List("Camping", "filming", "test"))
  }

}
