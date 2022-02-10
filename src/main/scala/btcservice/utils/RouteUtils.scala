package btcservice.utils

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.json4s.Formats
import org.json4s.JsonAST.{JNothing, JString}
import org.json4s.native.JsonMethods.parse
import org.json4s.native.Serialization.read

import scala.concurrent.{ExecutionContext, Future}

object ServerRoutes{
  val DepositRoute = "deposit"
  val HistoryRoute = "history"
}


trait RouteUtils {

  implicit val formats: Formats
  implicit val ec: ExecutionContext

  def parseJsonReqWithDate[T](requestStr: String)(implicit mf: Manifest[T]): Future[(T, DateTime)] = {
    val baseFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss")

    val req = read[T](requestStr)
    val date = parse(requestStr) \ "datetime" match {
      case JString(d) => Some(d)
      case JNothing   => None
    }

    Future((req, date.filter(_.nonEmpty).map(DateTime.parse(_, baseFormatter)).getOrElse(DateTime.now)))
  }
}
