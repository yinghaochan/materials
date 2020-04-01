// agent/src/Agent.scala
     while(true) try {
       val rpc = Shared.receive[Rpc](bytesIn)
+      rpc match{
+        case Rpc.IsDir(path) => Shared.send(bytesOut, os.isDir(os.pwd / path))
+        case Rpc.Exists(path) => Shared.send(bytesOut, os.exists(os.pwd / path))
+        case Rpc.ReadBytes(path) => Shared.send(bytesOut, os.read.bytes(os.pwd / path))
+        case Rpc.WriteOver(bytes, path) =>
+          os.remove.all(os.pwd / path)
+          Shared.send(bytesOut, os.write.over(dest / path, bytes, createFolders = true))
+      }
     } catch{case e: java.io.EOFException => System.exit(0)}
   }
 }
