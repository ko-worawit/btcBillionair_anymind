package btcservice.server

//#json-formats
import btcservice.model.{BtcHistoryRequest, BtcTransactionRequest}
import spray.json.DefaultJsonProtocol

object JsonFormats  {
  // import the default encoders for primitive types (Int, String, Lists etc)
  import DefaultJsonProtocol._

  implicit val BtcTrasactionJsonFormat = jsonFormat2(BtcTransactionRequest)
  implicit val BtcHistoryRequestJsonFormat = jsonFormat2(BtcHistoryRequest)

}
//#json-formats
