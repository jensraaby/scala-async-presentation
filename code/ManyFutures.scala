import scala.util.{Try,Success,Failure}
import scala.concurrent.{Future,Promise,blocking}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object ManyFutures extends App {

  println("Started running")

  def manyFutures: List[Future[String]] = {
    List(Future("back"), Future("to"), Future("the"), Future("future"))
  }

  Future.sequence(manyFutures) onComplete { 
    case Success(listOfStrings) => listOfStrings.map(println)
    case Failure(t) => println(t.getMessage)
  }
  println("Done!")





  delay(Duration(1000,"millis")) onComplete {
    case _ => println("hello")
  }



  def delay(t: Duration): Future[Unit] = {
      val p = Promise[Unit]()
      p.success(blocking {
        Thread.sleep(t.toMillis)
        })
      p.future
    }
}
