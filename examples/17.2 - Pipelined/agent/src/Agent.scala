// agent/src/Agent.scala
package sync
object Agent {
  def main(args: Array[String]): Unit = {
    val dest = os.Path(args(0), os.pwd)
    val bytesIn = new java.io.DataInputStream(System.in)
    val bytesOut = new java.io.DataOutputStream(System.out)

    while(true){
      val rpc = Shared.receive[Rpc](bytesIn)
      rpc match{
        case Rpc.IsDir(path) => Shared.send(bytesOut, os.isDir(dest / path))
        case Rpc.Exists(path) => Shared.send(bytesOut, os.exists(dest / path))
        case Rpc.ReadBytes(path) => Shared.send(bytesOut, os.read.bytes(dest / path))
        case Rpc.WriteOver(bytes, path) =>
          os.remove.all(dest / path)
          Shared.send(bytesOut, os.write.over(dest / path, bytes, createFolders = true))
      }
    }
  }
}