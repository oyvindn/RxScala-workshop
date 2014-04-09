package no.bekk.rxscala.util

object ThreadRunner {

  def apply(block: () => Unit) {
    new Thread(new Runnable {
      override def run() {
        block()
      }
    }).start()
  }

}
