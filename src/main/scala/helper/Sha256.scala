package helper

import java.security.MessageDigest

/**
  * Created by r.lavolee.
  */
object Sha256 {

  // no need to reset message digest this way
  def apply(bytes: Array[Byte]): Array[Byte] =
    MessageDigest.getInstance("SHA-256").digest(bytes)

  def apply(text: String): Array[Byte] =
    Sha256(text.getBytes("UTF-8"))
}
