package no.bekk.rxscala.util

class Connection {
  var pingCount = 0
  def ping: Boolean = {
    val res = pingCount < 10
    pingCount += 1
    res
  }
}

object Connection {
  def apply() = new Connection
}