import scala.util.{Try,Success,Failure}
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object ForExpFuture extends App {
  type Bean = String
  type Coffee = String

  println("Started running")

  def getSomeColombianBeans: Future[Bean] = Future {
    println("gathering beans")
    "Colombian"
  }
  def makeCoffee(beanType: Bean): Future[Coffee] = Future {
    println("making some coffee")
    s"Delicious $beanType coffee"
  }

  val futureCoffee = for {
    b <- getSomeColombianBeans
    coffee <- makeCoffee(b)
  } yield coffee

  futureCoffee map { coffee => println("Here you go: " + coffee) }


}
