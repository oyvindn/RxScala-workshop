package no.bekk.rxscala.examples

import rx.lang.scala.{Observable, Subject}
import rx.lang.scala.subjects.{BehaviorSubject, ReplaySubject, AsyncSubject}
import scala.concurrent.duration._

/*
 * A Subject is a sort of bridge or proxy that acts both as an Subscriber and as an Observable.
 * Because it is a Subscriber, it can subscribe to one or more Observables, and because it is an Observable,
 * it can pass through the items it observes by reemitting them, and it can also emit new items.
 *
 * If you have a Subject and you want to pass it along to some other agent without exposing its Subscriber interface,
 * you can mask it by calling its asObservable method, which will return the Subject as a pure Observable.
 */


/*
 * PublishSubject emits to a subscriber only those items that are emitted by the source Observable(s) subsequent
 * to the time of the subscription.
 */
object PublishSubjects extends App {
  val channel = Subject[Int]()

  val a = channel.subscribe(x => println(s"a: $x"))
  val b = channel.subscribe(x => println(s"b: $x"))

  channel.onNext(1)

  a.unsubscribe()

  channel.onNext(2)

  channel.onCompleted()

  val c = channel.subscribe(x => println(s"c: $x"))

  channel.onNext(3)
}

/*
 * AsyncSubject emits the last value (and only the last value) emitted by the source Observable,
 * and only after that source Observable completes. (If the source Observable does not emit any values,
 * the AsyncSubject also completes without emitting any values.)
 */
object AsyncSubjects extends App {
  val channel = AsyncSubject[Int]()

  val a = channel.subscribe(x => println(s"a: $x"))
  val b = channel.subscribe(x => println(s"b: $x"))

  channel.onNext(1)

  a.unsubscribe()

  channel.onNext(2)

  channel.onCompleted()

  val c = channel.subscribe(x => println(s"c: $x"))

  channel.onNext(3)

}

/*
 * ReplaySubject emits to any subscriber all of the items that were emitted by the source Observable(s),
 * regardless of when the subscriber subscribes.
 */
object ReplaySubjects extends App {
  val channel = ReplaySubject[Int]()

  val a = channel.subscribe(x => println(s"a: $x"))
  val b = channel.subscribe(x => println(s"b: $x"))

  channel.onNext(1)

  a.unsubscribe()

  channel.onNext(2)

  channel.onCompleted()

  val c = channel.subscribe(x => println(s"c: $x"))

  channel.onNext(3)
}

/*
 * When an Subscriber subscribes to a BehaviorSubject, it begins by emitting the item most recently emitted by the
 * source Observable (or a seed/default value if none has yet been emitted) and then continues to emit any other
 * items emitted later by the source Observable(s).
 */
object BehaviorSubjects extends App {
  val channel = BehaviorSubject[Int](0)

  val a = channel.subscribe(x => println(s"a: $x"))
  val b = channel.subscribe(x => println(s"b: $x"))

  channel.onNext(1)

  a.unsubscribe()

  channel.onNext(2)

  val c = channel.subscribe(x => println(s"c: $x"))

  channel.onNext(3)

  channel.onCompleted()

  val d = channel.subscribe(x => println(s"d: $x"))

  channel.onNext(3)

}

object ConnectableObservable extends App {
  val connectableObservable = Observable.timer(0 millis, 100 millis).publish

  val subscription = connectableObservable.connect

  val a = connectableObservable.subscribe(x => println(s"a: $x"))

  Thread.sleep((1 second).toMillis)

  val b = connectableObservable.subscribe(x => println(s"b: $x"))

  Thread.sleep((1 second).toMillis)

  a.unsubscribe()

  Thread.sleep((2 second).toMillis)

  val c = connectableObservable.subscribe(x => println(s"c: $x"))

  subscription.unsubscribe()

  Thread.sleep((1 second).toMillis)
}