package actor

import actor.MinerActor.{Mine, Result}
import akka.actor.{Actor, PoisonPill, Props}
import helper.Sha256
import model.{Block, Hash, ReversedHash}

/**
  * Created by r.lavolee.
  */
class MinerActor extends Actor {
  override def receive: Receive = {
    case m: MinerActor.Mine =>
      sender ! Result(mine(m), m.nonce)
      self ! PoisonPill
  }

  private def mine(m: Mine): ReversedHash = {
    val newBlock: Block = Block.withNewNonce(m.b, m.nonce)
    val header = Hash.toHex(newBlock.headerHex).toByteArray
    Hash(Sha256(Hash(Sha256(header)).value)).reverse
  }
}

object MinerActor {
  case class Mine(b: Block, nonce: Long)
  case class Result(rh: ReversedHash, nonce: Long)
  def props: Props = Props[MinerActor]
}