package btcservice.model

import org.joda.time.{DateTime, DateTimeZone}
import org.joda.time.format.DateTimeFormat

object ModelUtil {

  val baseFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ")

  def parseBtcTransactionRequest(request: BtcTransactionRequest): BtcTransaction = {
    val transactionDate =  DateTime.parse(request.datetime, baseFormatter)
    BtcTransaction(transactionDate, request.amount)
  }

  def parseBtcTransaction(btcTransaction: BtcTransaction): BtcTransactionRequest = {
    BtcTransactionRequest(btcTransaction.transactionDate.toString(baseFormatter), btcTransaction.amount)
  }

  def convertTransactionToUtc(btcTransaction: BtcTransaction):BtcTransaction = {
    BtcTransaction(convertToUtc(btcTransaction.transactionDate), btcTransaction.amount)
  }

  def parseBtcHistoryRequest(request: BtcHistoryRequest): BtcHistory = {
    BtcHistory(DateTime.parse(request.startDatetime, baseFormatter), DateTime.parse(request.endDatetime, baseFormatter))
  }

  def convertHistoryRequestToUtc(btcHistory: BtcHistory): BtcHistory = {
    BtcHistory(convertToUtc(btcHistory.startDate), convertToUtc(btcHistory.endDate))
  }

  private def convertToUtc(dateTime:DateTime) = dateTime.toDateTime(DateTimeZone.UTC)


}
