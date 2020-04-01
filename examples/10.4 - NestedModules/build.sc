// build.sc
import mill._

trait FooModule extends Module{
  import mill._

  def srcs = T.source(millSourcePath / "src")

  def concat = T{
    os.write(T.dest / "concat.txt", for(p <- os.list(srcs().path)) yield os.read(p))
    PathRef(T.dest / "concat.txt")
  }
}

object bar extends FooModule{
  object inner1 extends FooModule
  object inner2 extends FooModule
}
object wrapper extends Module{
  object qux extends FooModule
}
