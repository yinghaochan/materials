// agent/src/Agent.scala
package sync
object Agent {
  def main(args: Array[String]): Unit = {
    val dest = os.Path(args(0), os.pwd)
    val bytesIn = new java.io.DataInputStream(System.in)
    val bytesOut = new java.io.DataOutputStream(System.out)
    while(true) try {
      val rpc = Shared.receive[Rpc](bytesIn)
      System.err.println("Agent handling: " + rpc)
      rpc match{
        case Rpc.StatPath(path) =>
          Shared.send(
            bytesOut,
            Rpc.StatInfo(path, Shared.hashPath(dest / path))
          )

        case Rpc.WriteOver(bytes, path) =>
          os.remove.all(os.Path(path, dest))
          os.write.over(dest / path, bytes, createFolders = true)
      }
    }catch{
      case e: java.io.EOFException => return
    }
  }
}