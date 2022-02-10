package btcservice.server

import btcservice.model.{BtcHistoryRequest, BtcTransactionRequest, ModelUtil}
import spray.json.DefaultJsonProtocol._
import spray.json._

class BtcService {

  def addTransaction(btcTransactionRequest: BtcTransactionRequest): String = {

    val btcTransaction = ModelUtil.parseBtcTransactionRequest(btcTransactionRequest)

    TransactionAggregate.transactionActor ! BtcCommandMessage(btcTransaction)

    s"{result: Added ${btcTransaction.toString}}"
  }

  def getHistory(historyReq: BtcHistoryRequest): JsValue = {
    import JsonFormats._

    val btcHistory = ModelUtil.parseBtcHistoryRequest(historyReq)
    val result = TransactionAggregate.getTransactionHistory(btcHistory)
        .map(ModelUtil.parseBtcTransaction)

    result.toJson
  }
}
