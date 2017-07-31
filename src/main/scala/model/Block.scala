package model

/**
  * Created by r.lavolee.
  */
case class Block
(
  hash: Option[ReversedHash],
  header: BlockHeader
) {
  def headerHex: String =
    LittleEndian(header.version) + header.hashPrevBlock.reverse.toString + header.hashMerkleRoot.reverse.toString +
      LittleEndian(header.time) + LittleEndian(header.bits) + LittleEndian(header.nonce)
}

object Block {
  def withNewNonce(b: Block, n: Long): Block =
    b.copy(header = b.header.copy(nonce = n))
}