package no.bekk.rxscala.assignments

import org.scalatest.FlatSpec
import scala.concurrent._
import ExecutionContext.Implicits.global

class SimpleObservableFutureSpec extends FlatSpec {


  it should "create an blocking Observable from a Future" in {
    val f: Future[List[String]] = future {
      List("Camping", "filming", "test")
    }

    new SimpleObservableFuture().createObservableFuture(f).subscribe(result => {
      assert(result === List("Camping", "filming", "test"))
    })
  }

}
