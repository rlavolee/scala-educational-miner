package model

/**
  * Created by r.lavolee.
  */
case class LittleEndian(value: String) extends AnyVal {
  override def toString: String = value
}

object LittleEndian {
  def apply(s: Int): LittleEndian = format(s)
  def apply(s: Long): LittleEndian = format(s)

  private def format[T](s: T)(implicit t: Integral[T]): LittleEndian = {
    import t._
    LittleEndian("%08x".format(s.toLong()).grouped(2).toList.reverse.mkString)
  }
}
