package btcservice.server

import btcservice.model.BtcTransaction

///This is mock DB storage as in memory. For future scale we can make this class connection to DB instance
class TransactionDataStorage {

  private var transactionRepo =  scala.collection.mutable.ListBuffer[BtcTransaction]()

  def insertTransaction(btcTransaction: BtcTransaction) =
    transactionRepo.append(btcTransaction)

  def getTransactionRepo = transactionRepo

}
