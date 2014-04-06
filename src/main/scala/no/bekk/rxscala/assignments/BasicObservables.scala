package no.bekk.rxscala.assignments

import rx.lang.scala.Observable

object BasicObservables {

  /*
   * Create an observable that emits each of the elements in the list multiplied with 2
   */
  def observableFromList(list: List[Int]): Observable[Int] = Observable.from(list).map(_ * 2)

}
