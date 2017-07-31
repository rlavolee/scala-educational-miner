package model

/**
  * Created by r.lavolee.
  */
trait BaseHash {

  assert(value.length == 32)

  protected val value: Array[Byte]

  def < [T <: BaseHash](bHash: T): Boolean = format < bHash.toString

  private val format: String = value.map(v => "%02x".format(v)).mkString

  override def toString: String = format

  def reverse[T](apply: Array[Byte] => T): T = apply(value.reverse)
}
