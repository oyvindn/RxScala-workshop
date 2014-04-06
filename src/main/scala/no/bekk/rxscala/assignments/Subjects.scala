package no.bekk.rxscala.assignments

import rx.lang.scala.subjects.{ReplaySubject, AsyncSubject}
import rx.lang.scala.{Subject, Observable}


object Subjects {

  def concatObservableAndSubject(obs: Observable[Int], repSub: Subject[Int]) = {
    obs ++ repSub
  }
}
