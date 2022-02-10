package btcservice.server

import akka.http.scaladsl.Http

object BtcServer {

  def main(args: Array[String]): Unit = {


    implicit val btcSystem = akka.actor.ActorSystem("btcservice")

    //#server-bootstrapping
    //val userRegistryActor = context.spawn(BtcController(), "UserRegistryActor")
    //context.watch(userRegistryActor)

    val routes = new BtcRoutes()(btcSystem)

    Http().newServerAt( "localhost", 8080).bind(routes.btcServerRoute)

    //#server-bootstrapping
  }

}
