package no.bekk.rxscala.assignments

import org.scalatest.FlatSpec
import scala.concurrent._
import ExecutionContext.Implicits.global

class SimpleObservableFutureSpec extends FlatSpec {


  it should "create an blocking Observable from a Future" in {
    val f: Future[List[String]] = future {
      List("Camping", "filming", "test")
    }

   val result = SimpleObservableFuture.createObservableFuture(f).toBlockingObservable.toList
   assert(result.size === 1)
   assert(result.head === List("Camping", "filming", "test"))
  }

}
