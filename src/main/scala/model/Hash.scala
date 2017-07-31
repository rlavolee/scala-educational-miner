package model

/**
  * Created by r.lavolee.
  */
case class Hash(value: Array[Byte]) extends BaseHash {
  lazy val reverse: ReversedHash = ReversedHash(value.reverse)
}

object Hash {
  def apply(value: BigInt): Hash = {
    val notPadded = value.toByteArray
    Hash(Array.fill(32 - notPadded.length)(0.toByte) ++ notPadded)
  }
  def apply(value: String): Hash = {
    assert(value.length == 64)
    Hash(toHex(value))
  }

  def toHex(value: String) = BigInt(value, 16)
}