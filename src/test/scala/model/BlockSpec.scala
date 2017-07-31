package model

import org.scalatest.{Matchers, WordSpecLike}


/**
  * Created by r.lavolee.
  */
class BlockSpec extends WordSpecLike with Matchers {

  val block = Block(None, BlockHeader(
    1,
    Hash("00000000000008a3a41b85b8b29ad444def299fee21793cd8b9e567eab02cd81"),
    Hash("2b12fcf1b09288fcaff797d71e950e71ae42b91e8bdb2304758dfcffc2b620e3"),
    1305998791,
    440711666,
    2504433986L
  ))

  val blockWithNewNonce = Block(None, BlockHeader(
    1,
    Hash("00000000000008a3a41b85b8b29ad444def299fee21793cd8b9e567eab02cd81"),
    Hash("2b12fcf1b09288fcaff797d71e950e71ae42b91e8bdb2304758dfcffc2b620e3"),
    1305998791,
    440711666,
    1L
  ))

  "⛏  A Block" when {
     "created" should {
        "concat correctly the header" in {
            block.headerHex shouldEqual "01000000" +
            "81cd02ab7e569e8bcd9317e2fe99f2de44d49ab2b8851ba4a308000000000000" +
            "e320b6c2fffc8d750423db8b1eb942ae710e951ed797f7affc8892b0f1fc122b" +
            "c7f5d74d" +
            "f2b9441a" +
            "42a14695"
      }
      "copy a new block with a new nonce" in {
        Block.withNewNonce(block, 1L).headerHex shouldEqual blockWithNewNonce.headerHex
      }
    }
  }

}
