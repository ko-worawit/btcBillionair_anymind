package btcservice.server

import akka.actor.{Actor, ActorSystem, Props}
import btcservice.model.{BtcHistory, BtcTransaction, ModelUtil}

case class BtcCommandMessage(trans: BtcTransaction)

class TransactionActor extends Actor {

  override def receive: Receive = {
    case repositoryCommand: BtcCommandMessage => {
        val utcTransaction = ModelUtil.convertTransactionToUtc(repositoryCommand.trans)
        TransactionAggregate.transactionDataStorage.insertTransaction(utcTransaction)
    }
  }
}

object TransactionAggregate {
  lazy val transactionActorSystem = ActorSystem("btc-transaction")
  lazy val transactionActor = transactionActorSystem.actorOf(Props[TransactionActor])

  val transactionDataStorage = new TransactionDataStorage


  def getTransactionHistory(queryHistory: BtcHistory) = {

    val utcHistory = ModelUtil.convertHistoryRequestToUtc(queryHistory)

    filterTransactionHistoryDate(utcHistory, transactionDataStorage.getTransactionRepo.toList)
  }

  def filterTransactionHistoryDate(btcHistory: BtcHistory, transactionList: List[BtcTransaction]) = {
    transactionList.filter(trans =>
      (trans.transactionDate.isAfter(btcHistory.startDate) && trans.transactionDate.isBefore(btcHistory.endDate)) ||
      (trans.transactionDate.isEqual(btcHistory.startDate) || trans.transactionDate.isEqual(btcHistory.endDate)))
  }

}
