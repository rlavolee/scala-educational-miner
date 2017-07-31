package model

import org.scalatest.{Matchers, WordSpecLike}

/**
  * Created by r.lavolee.
  */
class LittleEndianSpec extends WordSpecLike with Matchers  {

  val littleEndian = LittleEndian(42)

  "⛏  A LittleEndian" when {
     "created" should {
        "return a well format string" in {
          littleEndian.toString shouldBe "2a000000"
      }
    }
  }

  "⛏  A LittleEndian" should {
    "be created with Int" in {
      LittleEndian(42) shouldEqual LittleEndian("2a000000")
    }
    "be created with Long" in {
      LittleEndian(20160807L) shouldEqual LittleEndian("27a13301")
    }
  }

}
