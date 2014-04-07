package no.bekk.rxscala.assignments

import rx.lang.scala.Observable

object BasicObservables {

  /*
   * Create an observable that emits each of the elements in the list multiplied with 2
   */
  def observableFromList(list: List[Int]): Observable[Int] = ???



  /*
   * Create an observable that for each time there is a value from both lists, emits the sum of the who values
   */
  def observableSums(o1: Observable[Int], o2: Observable[Int]): Observable[Int] = ???



  /*
   * Create an observable that emits the sum of the 10 last positive numbers emitted by the given observable
   */
  def observableSumLastTen(observable: Observable[Int]): Observable[Int] = ???

}
