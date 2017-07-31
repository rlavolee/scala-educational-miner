package model

/**
  * Created by r.lavolee.
  */
case class ReversedHash(value: Array[Byte]) extends BaseHash {
  lazy val reverse: Hash = Hash(value.reverse)
}

object ReversedHash {
  def apply(hash: Hash): ReversedHash = hash.reverse
}