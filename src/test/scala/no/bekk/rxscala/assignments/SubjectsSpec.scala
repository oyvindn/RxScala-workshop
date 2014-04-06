package no.bekk.rxscala.assignments

import org.scalatest.FlatSpec
import scala.concurrent._
import scala.util.{Success, Failure, Try}
import scala.collection.mutable
import rx.lang.scala.subjects.{BehaviorSubject, ReplaySubject, AsyncSubject, PublishSubject}
import ExecutionContext.Implicits.global
import scala.concurrent.duration._
import rx.lang.scala.{Subject, Observable}
import rx.subjects.PublishSubject


class SubjectsSpec extends FlatSpec {

  it should "use subject to get more values on to an existing observable" in {

    val subject = Subject[Int]() //TASK: Replace with correct subject type
    val initial = Observable.from(List(1,2,3,4))
    val target = Subjects.concatObservableAndSubject(initial, subject)

    subject.onNext(5)

    val subscription1 = target subscribe(List(_))

    subject.onNext(6)
    subject.onCompleted()
    subscription1.unsubscribe()
    subject.onNext(7)

    val f = future(target.toBlockingObservable.toList)

    Try(Await.result(f, 100 millis)) match {
      case Success(res) => {
        assert(res contains 1)
        assert(res contains 2)
        assert(res contains 3)
        assert(res contains 4)
        assert(res contains 5)
        assert(res contains 6)
        assert(!res.contains(7))
      }
      case Failure(e) => fail("Subjects failed")
    }

  }

}
