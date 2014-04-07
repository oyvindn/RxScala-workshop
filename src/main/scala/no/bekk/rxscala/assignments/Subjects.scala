package no.bekk.rxscala.assignments

import rx.lang.scala.{Subscription, Subject, Observable}
import rx.lang.scala.subjects.ReplaySubject


object Subjects {

  var total = 0

  /*
   * Create an subscription from the subject which sums up each value on the stream
   * Save the result in the total variable
   */
  def createSumSubscriber(subject: Subject[Int]): Subscription = ???


  /*
   * Create an observable by concating a subject to the initial observer
   * Add values to the subject stream
   */
  def concatObservableAndSubject(initial: Observable[Int]): Observable[Int] = ???
}
