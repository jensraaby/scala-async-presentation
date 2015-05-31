import scala.util.{Try,Success,Failure}
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object RecoveringFuture extends App {

  def getSomeElectricity = Future { sys.error("It's not invented yet") }
  def getSomeEmergencyPower = Future { "Here's 1.21 gigawatts" }

  val power: Future[String] = getSomeElectricity recoverWith {
    case t: Throwable =>
      println(t.getMessage)
      getSomeEmergencyPower
  }

  power onComplete {
    case Success(t) => println(t)
    case Failure(t) => println("Sounds pretty heavy")
  }

}
