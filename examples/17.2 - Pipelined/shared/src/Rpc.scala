// shared/src/Rpc.scala
package sync
import upickle.default.{readwriter, ReadWriter, macroRW}
sealed trait Rpc
object Rpc{
  implicit val relPathRw = readwriter[String].bimap[os.RelPath](_.toString, os.RelPath(_))

  case class IsDir(path: os.RelPath) extends Rpc
  implicit val isDirRw: ReadWriter[IsDir] = macroRW

  case class Exists(path: os.RelPath) extends Rpc
  implicit val existsRw: ReadWriter[Exists] = macroRW

  case class ReadBytes(path: os.RelPath) extends Rpc
  implicit val readBytesRw: ReadWriter[ReadBytes] = macroRW

  case class WriteOver(src: Array[Byte], path: os.RelPath) extends Rpc
  implicit val writeOverRw: ReadWriter[WriteOver] = macroRW

  implicit val rpcRw: ReadWriter[Rpc] = macroRW
}