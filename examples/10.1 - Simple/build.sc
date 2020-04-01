// build.sc
import mill._

def srcs = T.source(millSourcePath / "src")

def concat = T{
  os.write(T.dest / "concat.txt", for(p <- os.list(srcs().path)) yield os.read(p))
  PathRef(T.dest / "concat.txt")
}