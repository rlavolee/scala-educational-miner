package model

case class Target(value: Hash) extends AnyVal {
  override def toString: String = value.toString
}

object Target {
  def apply(value: Long): Target = {
    val exp = value >> 24
    val mant: BigInt = value & 0xffffff
    val p: BigInt = mant * (BigInt(1) << (8*(exp.toInt - 3)))
    Target(Hash(p))
  }
}