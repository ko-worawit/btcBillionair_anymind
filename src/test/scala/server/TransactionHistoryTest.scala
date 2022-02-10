package server

import btcservice.model.{BtcHistory, BtcTransaction}
import btcservice.model.ModelUtil.baseFormatter
import btcservice.server.TransactionAggregate
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.scalatest.funsuite.AnyFunSuite

class TransactionHistoryTest extends AnyFunSuite{

  val baseFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ")


  val dummyTransaction = BtcTransaction(DateTime.parse("2019-10-05T15:45:05+07:00", baseFormatter), 10)



  test("fetch correct transaction") {
    val transaction_1 = dummyTransaction.copy(transactionDate = DateTime.parse("2019-10-05T15:45:05+07:00"))
    val transaction_2 = dummyTransaction.copy(transactionDate = DateTime.parse("2019-10-05T16:45:05+07:00"))
    val transaction_3 = dummyTransaction.copy(transactionDate = DateTime.parse("2019-10-05T17:45:05+07:00"))

    val reqHistory = BtcHistory(DateTime.parse("2019-10-05T15:45:05+07:00"), DateTime.parse("2019-10-05T16:45:05+07:00"))

    val result = TransactionAggregate.filterTransactionHistoryDate(reqHistory, List(transaction_1, transaction_2, transaction_3))

    assert(result == List(transaction_1, transaction_2))
  }

  test("fetch correct transaction with time zone diff") {
    val transaction_1 = dummyTransaction.copy(transactionDate = DateTime.parse("2019-10-05T15:45:05+07:00"))
    val transaction_2 = dummyTransaction.copy(transactionDate = DateTime.parse("2019-10-05T16:45:05+07:00"))
    val transaction_3 = dummyTransaction.copy(transactionDate = DateTime.parse("2019-10-05T17:45:05+09:00"))

    val reqHistory = BtcHistory(DateTime.parse("2019-10-05T15:45:05+07:00"), DateTime.parse("2019-10-05T16:45:05+07:00"))

    val result = TransactionAggregate.filterTransactionHistoryDate(reqHistory, List(transaction_1, transaction_2, transaction_3))

    assert(result == List(transaction_1, transaction_2, transaction_3))
  }
}
