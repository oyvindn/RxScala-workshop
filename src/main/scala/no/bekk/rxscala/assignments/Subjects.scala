package no.bekk.rxscala.assignments

import rx.lang.scala.{Subscription, Subject, Observable}


object Subjects {

  var total = 0

  def createSumSubscriber(subject: Subject[Int]): Subscription = {
    subject.subscribe(n => total += n)
  }

  def concatObservableAndSubject(obs: Observable[Int], repSub: Subject[Int]) = {
    obs ++ repSub
  }
}
