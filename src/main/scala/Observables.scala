import java.util.concurrent.CountDownLatch
import rx.lang.scala.Observable

/*
 * To create an Observable, you can either implement an Observable that (synchronously or asynchronously)
 * executes and emits data by invoking a Subscriber’s onNext() method, or you can convert an existing data
 * structure into an Observable.
 */


/*
 * These converted Observables will synchronously invoke the onNext() method of any Subscriber that
 * subscribes to them, for each item emitted by the Observable, and will then invoke the Subscriber’s
 * onCompleted() method.
 */
object CreatingObservablesFromExistingDataStructures extends App {
  val numbers = List(1, 2, 3, 4, 5)
  val observable = Observable.from(numbers)

  observable subscribe(n => println(n))
}


/*
 * You can implement asynchronous IO, computational operations, or “infinite” streams of data by using
 * the Observable class.
 */

/*
 * Observable that blocks when subscribed to (does not spawn an extra thread)
 */
object ImplementingAnSynchronousObservable extends App {
  def customBlockingObservable(): Observable[String] = {
    Observable(subscriber => {
      0 to 50 foreach (n => if (!subscriber.isUnsubscribed) subscriber.onNext(s"value $n"))

      // after sending all values we complete the sequence
      subscriber.onCompleted()
    })
  }

  customBlockingObservable().subscribe(it => println(it))
}


/*
 * Observable that does not block when subscribed to as it spawns a separate thread.
 */
object ImplementingAnAsynchronousObservable extends App {
  def customNonBlockingObservable(): Observable[String] = {
    Observable(subscriber => {
      // For simplicity this example uses a Thread instead of an ExecutorService/ThreadPool
      val t = new Thread(new Runnable {
        override def run() {
          0 to 50 foreach (n => if (!subscriber.isUnsubscribed) subscriber.onNext(s"value $n"))

          // after sending all values we complete the sequence
          subscriber.onCompleted()
        }
      }).start()
    })
  }

  customNonBlockingObservable().subscribe(it => println(it))
}