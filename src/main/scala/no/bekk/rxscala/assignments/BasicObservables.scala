package no.bekk.rxscala.assignments

import rx.lang.scala.Observable
import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.duration._
import ExecutionContext.Implicits.global

object BasicObservables {

  /*
   * Create an observable that emits each of the elements in the list multiplied with 2
   */
  def observableFromList(list: List[Int]): Observable[Int] = Observable.from(list).map(_ * 2)



  /*
   * Create an observable that for each time there is a value from both lists, emits the sum of the who values
   */
  def observableSums(o1: Observable[Int], o2: Observable[Int]): Observable[Int] = o1.zip(o2).map { case (x,y) => x + y }



  /*
   * Create an observable that emits the sum of the 10 last positive numbers emitted by the given observable
   */
  def observableSumLastTen(observable: Observable[Int]): Observable[Int] = observable.filter(_ > 0).takeRight(10).sum



  /*
   * Create an observable emitting the value produced by the Future as its single item.
   */
  def observableFuture(future: Future[List[String]]): Observable[List[String]] = Observable.from(future)



  /*
   * Create an observable using a timer or interval that emits a new Int every 100ms, and transform it to emit buffers of 5 values at a time
   */
  def observableTimer: Observable[Seq[Long]] = Observable.interval(100 millisecond).buffer(5)
}
