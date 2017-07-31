package model

import org.scalatest.{Matchers, WordSpecLike}

/**
  * Created by r.lavolee.
  */
class ReverseHashSpec extends WordSpecLike with Matchers {

  val h = Hash("00000000000008a3a41b85b8b29ad444def299fee21793cd8b9e567eab02cd81")
  val rhByte = ReversedHash(Array[Byte](-127, -51, 2, -85, 126, 86, -98, -117, -51, -109, 23, -30, -2, -103, -14, -34, 68, -44, -102, -78, -72, -123, 27, -92, -93, 8, 0, 0, 0, 0, 0, 0))

  "⛏  A ReversedHash" when {
    "created" should {
      "be equal to 81cd02ab7e569e8bcd9317e2fe99f2de44d49ab2b8851ba4a308000000000000" in {
        rhByte.toString shouldEqual "81cd02ab7e569e8bcd9317e2fe99f2de44d49ab2b8851ba4a308000000000000"
      }

      "be 32 long while accessing the value" in {
        rhByte.value.length shouldBe 32
      }

      "be 64 long while stringify" in {
        rhByte.toString.length shouldBe 64
      }

      "correctly swap bytes order" in {
        rhByte.reverse.value shouldEqual h.value
      }
    }
  }

  "⛏  ReversedHash" should {
    "be created with Hash" in {
      ReversedHash(h).value shouldEqual rhByte.value
    }
  }

}
