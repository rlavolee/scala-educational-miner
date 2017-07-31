package actor

import actor.MinerActor.Mine
import akka.actor.{Actor, ActorLogging, ActorRef, PoisonPill, Props}
import akka.util.Timeout
import model._

import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration._
import scala.language.postfixOps

/**
  * Created by r.lavolee.
  */
class MinerManagerActor extends Actor with ActorLogging {

  case class ValidHash(r: MinerActor.Result)

  case object InvalidHash
  case object SpawnMiner

  private val totalMiners = 1000
  private var nonce = 429798190L

  implicit val timeout = Timeout(10 hours)
  implicit val ec: ExecutionContextExecutor = context.system.dispatcher

  override def receive: Receive = {
    case b: Block =>
      context.become(receiveWithBlock(b, Target(b.header.bits), sender))
      (0 to totalMiners).foreach(_ => spawnMinerActor(b))
  }

  def receiveWithBlock(b: Block, t: Target, sender: ActorRef): Receive = {
    case r: MinerActor.Result =>
      if (isBlockValid(r.rh, t)) self ! ValidHash(r)
      else self ! InvalidHash

    case ValidHash(r) =>
      context.become(receive)
      context.children.foreach(_ ! PoisonPill)
      sender ! b.copy(hash = Some(r.rh), header = b.header.copy(nonce = r.nonce))
      nonce = 0L

    case InvalidHash =>
      spawnMinerActor(b)

    case x => log.warning(s"MinerManagerActor cannot handle the following message: ${x.toString}")
  }

  private def spawnMinerActor(b: Block) = {
    context.actorOf(MinerActor.props) ! Mine(b, nonce)
    nonce = nonce + 1
  }

  private def isBlockValid[T <: BaseHash](h: T, t: Target): Boolean = h < t.value
}

object MinerManagerActor {
  def props: Props = Props[MinerManagerActor]
}