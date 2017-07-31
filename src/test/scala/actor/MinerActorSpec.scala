package actor

import actor.MinerActor.{Mine, Result}
import akka.actor.{ActorRef, ActorSystem}
import akka.util.Timeout
import akka.pattern.ask
import model._
import org.scalatest.{Matchers, WordSpecLike}

import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration._
import scala.language.postfixOps

class MinerActorSpec extends WordSpecLike with Matchers {

  val system = ActorSystem()
  val minerRef: ActorRef = system.actorOf(MinerActor.props)

  val b1 = Block(None, BlockHeader(
    1,
    Hash("000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f"),
    Hash("0e3e2357e806b6cdb1f70b54c3a3a17b6714ee1f0e68bebb44a74b1efd512098"),
    1231466065,
    486604799,
    2573394689L
  ))

  val b44 = Block(None, BlockHeader(
    1,
    Hash("00000000ac21f2862aaab177fd3c5c8b395de842f84d88c9cf3420b2d393e550"),
    Hash("439aee1e1aa6923ad61c1990459f88de1faa3e18b4ee125f99b94b82e1e0af5f"),
    1231610361,
    0x1d00ffff,
    429798192L
  ))

  val b125552 = Block(None, BlockHeader(
    1,
    Hash("00000000000008a3a41b85b8b29ad444def299fee21793cd8b9e567eab02cd81"),
    Hash("2b12fcf1b09288fcaff797d71e950e71ae42b91e8bdb2304758dfcffc2b620e3"),
    1305998791,
    440711666,
    2504433986L
  ))
  //Jan 10, 2009 6:59:21 PM
  implicit val timeout = Timeout(10 seconds)
  implicit val ec: ExecutionContextExecutor = system.dispatcher


  "⛏  MinerActor" should {
    "mine Block#125552" in {
      (minerRef ? Mine(b125552, 2504433986L)).map{
        case r: Result =>
          println(s"PLOP#125552 ${r.rh.toString}")
          r.rh shouldEqual ReversedHash(Array[Byte](0, 0, 0, 0, 0, 0, 0, 0, 30, -115, 104, 41, -88, -94, 26, -36, 93, 56, -48, -92, 115, -79, 68, -74, 118, 87, -104, -26, 31, -104, -67, 29))
        case _ => false
      }
    }
    "mine Block#1" in {
      (minerRef ? Mine(b1, 2573394689L)).map{
        case r: Result =>
          r.rh shouldEqual ReversedHash(Array[Byte](0, 0, 0, 0, -125, -102, -114, 104, -122, -85, 89, 81, -41, 111, 65, 20, 117, 66, -118, -4, -112, -108, 126, -29, 32, 22, 27, -65, 24, -21, 96, 72))
        case _ => false
      }
    }
    "mine Block#44" in {
      (minerRef ? Mine(b44, 429798192L)).map{
        case r: Result =>
          println(s"PLOP ${r.rh.toString}")
          println(s"PLOP ${r.rh.reverse}")
          println(s"PLOP ${r.rh.value}")
          r.rh shouldEqual ReversedHash(Array[Byte](0, 0, 0, 0, 41, 120, -18, -51, -24, -48, 32, -9, -16, 87, 8, 59, -55, -112, 0, 47, -1, 73, 81, 33, -41, -36, 28, 38, -48, 12, 0, -8))
        case _ => false
      }
    }
  }

}
