import scala.util.{Try,Success,Failure}
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object FailingFuture extends App {

  println("Started running")

  val eventuallyHello = Future {
    sys.error("Something horrible went wrong")
  }

  eventuallyHello onComplete { 
    case Success(value) => println("Happy: " + value)
    case Failure(throwable) => println("Sad: " + throwable.getMessage)
  }

  println("Done!")
}
