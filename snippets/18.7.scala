// agent/src/Agent.scala
package sync
object Agent {
  def main(args: Array[String]): Unit = {
    val bytesIn = new java.io.DataInputStream(System.in)
    val bytesOut = new java.io.DataOutputStream(System.out)
    while(true) try {
      val rpc = Shared.receive[Rpc](bytesIn)
      System.err.println("Agent handling: " + rpc)
      rpc match{
        case Rpc.StatPath(path) =>
          Shared.send(bytesOut, Rpc.StatInfo(path, Shared.hashPath(os.pwd / path)))

        case Rpc.WriteOver(bytes, path) =>
          os.remove.all(os.Path(path, os.pwd))
          os.write.over(os.pwd / path, bytes, createFolders = true)
      }
    }catch{ case e: java.io.EOFException => System.exit(0) }
  }
}
