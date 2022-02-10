package btcservice.model

import org.joda.time.DateTime


case class BtcTransactionRequest(datetime: String, amount: Long)
case class BtcHistoryRequest(startDatetime: String, endDatetime: String)

case class BtcTransaction(transactionDate: DateTime, amount: Long)
case class BtcHistory(startDate: DateTime, endDate: DateTime)
