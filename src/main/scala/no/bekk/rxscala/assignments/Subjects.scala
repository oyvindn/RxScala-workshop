package no.bekk.rxscala.assignments

import rx.lang.scala.{Subscription, Subject, Observable}


object Subjects {

  var total = 0

  /*
   * Create an subscription from the subject which sums up each value on the stream
   * Save the result in the total variable
   */
  def createSumSubscriber(subject: Subject[Int]): Subscription = {
    subject.subscribe(n => total += n)
  }


  /*
   * Create an observable by concating the subject with the initial observer
   */
  def concatObservableAndSubject(initial: Observable[Int], subject: Subject[Int]) = {
    initial ++ subject
  }
}
