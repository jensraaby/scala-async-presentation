import scala.concurrent.Future
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object BasicFuture extends App {

  println("Started running")
  val eventuallyHello = Future("Hello there")
  eventuallyHello onSuccess {
    case value: String =>
      println(value)
  }

  println("Done!")
}
