package btcservice.server

import akka.actor.ActorSystem
import akka.http.scaladsl.model.{HttpEntity, HttpResponse, MediaTypes, StatusCodes}
import akka.http.scaladsl.server.Directives.pathPrefix
import btcservice.model.{BtcHistoryRequest, BtcTransactionRequest}
import akka.http.scaladsl.server.Route
import akka.util.Timeout
import btcservice.utils.ServerRoutes
import akka.http.scaladsl.server.Directives._

class BtcRoutes(implicit val system: ActorSystem) {

  val btcService = new BtcService()

  import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
  import JsonFormats._

  // If ask takes more time than this to complete the request is failed
  private implicit val timeout = Timeout.create(system.settings.config.getDuration("btc-server.request-timeout"))

  val btcServerRoute: Route =
    pathPrefix(ServerRoutes.DepositRoute){
      concat(
        pathEnd{
          concat(
            post{
              entity(as[BtcTransactionRequest]) { btctrans =>
                  complete(
                    btcService.addTransaction(btctrans)
                  )
              }
            }
          )
        }
      )
    } ~
      pathPrefix(ServerRoutes.HistoryRoute){
        concat(
          pathEnd{
            concat(
              post{
                entity(as[BtcHistoryRequest]) { historyReq =>
                  complete(
                    btcService.getHistory(historyReq)
                  )
                }
              }
            )
          }
        )
      }

}


