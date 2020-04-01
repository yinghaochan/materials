// build.sc
import mill._

val items = os.list(millSourcePath / "foo").map(_.last)
interp.watch(millSourcePath / "foo")

object foo extends Cross[FooModule](items:_*)
class FooModule(label: String) extends Module{
  import mill._

  def srcs = T.source(millSourcePath / "src")

  def concat = T{
    os.write(T.dest / "concat.txt", for(p <- os.list(srcs().path)) yield os.read(p))
    PathRef(T.dest / "concat.txt")
  }
}