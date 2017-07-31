package model

import org.scalatest.{Matchers, WordSpecLike}

/**
  * Created by r.lavolee.
  */
class HashSpec extends WordSpecLike with Matchers {

  val h = Hash("00000000000008a3a41b85b8b29ad444def299fee21793cd8b9e567eab02cd81")
  val target = Hash("00000000000044b9f20000000000000000000000000000000000000000000000")
  val hByte = Hash(Array[Byte](0, 0, 0, 0, 0, 0, 8, -93, -92, 27, -123, -72, -78, -102, -44, 68, -34, -14, -103, -2, -30, 23, -109, -51, -117, -98, 86, 126, -85, 2, -51, -127))
  val reservedHash = ReversedHash(h)

  "⛏  A Hash" when {
    "created" should {
      "be equal to 00000000000008a3a41b85b8b29ad444def299fee21793cd8b9e567eab02cd81" in {
        h.toString shouldEqual "00000000000008a3a41b85b8b29ad444def299fee21793cd8b9e567eab02cd81"
      }

      "be 32 long while accessing the value" in {
        h.value.length shouldBe 32
      }

      "be 64 long while stringify" in {
        h.toString.length shouldBe 64
      }

      "correctly swap bytes order" in {
        h.reverse shouldEqual reservedHash
      }

      "correctly compare Hash" in {
        h < target
      }
    }
  }

  "⛏  Hash" should {
    "be created with String" in {
      Hash("00000000000008a3a41b85b8b29ad444def299fee21793cd8b9e567eab02cd81").value shouldEqual hByte.value
    }
    "be created with BigInt" in {
      Hash(BigInt("13882695841356552174667387276879920379206201798250656891587969")).value shouldEqual hByte.value
    }
  }

}
