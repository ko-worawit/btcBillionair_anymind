package btcservice.server

import btcservice.model.{BtcHistory, BtcTransaction, ModelUtil}
import org.joda.time.DateTime

import scala.collection.mutable

class RepositoryQueryService {

  var readTransactionRepo = mutable.HashMap[DateTime, List[BtcTransaction]]()


  def getTransactionHistory(queryHistory: BtcHistory) = {

    val utcHistory = ModelUtil.convertHistoryRequestToUtc(queryHistory)

    readTransactionRepo
      .filter(repo => repo._1.isAfter(utcHistory.startDate) && repo._1.isBefore(utcHistory.endDate))

  }

}

