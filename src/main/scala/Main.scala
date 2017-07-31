import actor.MinerManagerActor

import akka.actor.ActorSystem
import akka.pattern.ask
import akka.util.Timeout
import model.{Block, BlockHeader, Hash}

import scala.concurrent.duration._
import scala.language.postfixOps

/**
  * Created by r.lavolee.
  */
object Main extends App {

  val system = ActorSystem()
  val minerManagerRef = system.actorOf(MinerManagerActor.props)

  implicit val timeout = Timeout(10 hours)
  implicit val ec = system.dispatcher

  val b44 = Block(None, BlockHeader(
    1,
    Hash("00000000ac21f2862aaab177fd3c5c8b395de842f84d88c9cf3420b2d393e550"),
    Hash("439aee1e1aa6923ad61c1990459f88de1faa3e18b4ee125f99b94b82e1e0af5f"),
    1231610361,
    0x1d00ffff
  ))

  (minerManagerRef ? b44).map{
    case b: Block =>
      println(b.hash)
  }
}
