package model

/**
  * Created by r.lavolee.
  */
case class BlockHeader
(
  version: Long = 1,
  hashPrevBlock: Hash,
  hashMerkleRoot: Hash,
  time: Long,
  bits: Long,
  nonce: Long = 0
)

object BlockHeader