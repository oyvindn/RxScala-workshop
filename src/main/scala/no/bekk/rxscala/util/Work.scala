package no.bekk.rxscala.util

import scala.concurrent.duration.Duration

object Work {

  type Calculation = () => Int

  def work(duration: Duration, result: Int): Int = {
    Thread.sleep(duration.toMillis)
    result
  }

}
