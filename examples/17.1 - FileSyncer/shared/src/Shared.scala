// shared/src/Shared.scala
package sync
import upickle.default.{Reader, Writer}
import java.io.{DataOutputStream, DataInputStream}

object Shared{
  def send[T: upickle.default.Writer](out: java.io.DataOutputStream, msg: T): Unit = {
    val bytes = upickle.default.writeBinary(msg)
    out.writeInt(bytes.length)
    out.write(bytes)
    out.flush()
  }

  def receive[T: upickle.default.Reader](in: java.io.DataInputStream) = {
    val buf = new Array[Byte](in.readInt())
    in.readFully(buf)
    upickle.default.readBinary[T](buf)
  }
}