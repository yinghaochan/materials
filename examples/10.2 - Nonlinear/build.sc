import mill._

def srcs = T.source(millSourcePath / "src")
def resources = T.source(millSourcePath / "resources")

def concat = T{
  os.write(T.dest / "concat.txt", for(p <- os.list(srcs().path)) yield os.read(p))
  PathRef(T.dest / "concat.txt")
}

def compress = T{
  for(p <- os.walk(resources().path) if os.isFile(p)){
    val copied = T.dest / p.relativeTo(resources().path)
    os.copy(p, copied, createFolders = true)
    os.proc("gzip", copied).call()
  }
  PathRef(T.dest)
}

def zipped = T{
  val temp = T.dest / "temp"
  os.makeDir(temp)
  os.copy(concat().path, temp / "concat.txt")
  for(p <- os.list(compress().path)) os.copy(p, temp / p.relativeTo(compress().path))
  os.proc("zip", "-r", T.dest / "out.zip", ".").call(cwd = temp)
  PathRef(T.dest / "out.zip")
}