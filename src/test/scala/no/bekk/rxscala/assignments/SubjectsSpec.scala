package no.bekk.rxscala.assignments

import org.scalatest._
import org.scalatest.FlatSpec
import scala.concurrent._
import scala.util.Try
import ExecutionContext.Implicits.global
import scala.concurrent.duration._
import rx.lang.scala._
import scala.util.Success
import scala.util.Failure

class SubjectsSpec extends FlatSpec with Matchers {

  it should "create subsribers that sums up each value on subject stream" in {
    val subject = Subject[Int]()

    val a = Subjects.createSumSubscriber(subject)
    val b = Subjects.createSumSubscriber(subject)

    subject.onNext(1)
    subject.onNext(2)

    a.unsubscribe()
    subject.onNext(3)

    subject.onCompleted()

    assert(Subjects.total === 1 + 1 + 2 + 2 + 3)

  }

  it should "use subject to get more values on to an existing observable" in {

    val subject = Subject[Int]() //TASK: Replace with correct subject type
    val initial = Observable.from(List(1,2,3,4))
    val target = Subjects.concatObservableAndSubject(initial, subject)

    subject.onNext(5)
    subject.onNext(6)
    subject.onCompleted()
    subject.onNext(7)

    val f = future(target.toBlockingObservable.toList)

    Try(Await.result(f, 100 millis)) match {
      case Success(res) =>
        res should contain allOf (1,2,3,4,5,6)
        assert(!res.contains(7))
      case Failure(e) => fail("Subjects failed")
    }

  }

}
