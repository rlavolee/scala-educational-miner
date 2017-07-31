package helper

import org.scalatest.{Matchers, WordSpecLike}

/**
  * Created by r.lavolee.
  */
class Sha256Spec extends WordSpecLike with Matchers {

  "⛏  Sha256 helper" should {
      "hash a string" in {
        Sha256("bob") shouldEqual Array[Byte](-127, -74, 55, -40, -4, -46, -58, -38, 99, 89, -26, -106, 49, 19, -95, 23, 13, -25, -107, -28, -73, 37, -72, 77, 30, 11, 76, -3, -98, -59, -116, -23)
    }
  }

}
