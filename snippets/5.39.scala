trait ConsoleParser{ def parse(s: String): Int }
object ConsoleParseInt extends ConsoleParser{
  def parse(): Int = scala.Console.in.readLine().toInt
}
object ConsoleParseBoolean extends ConsoleParser{ ... }
object ConsoleParseDouble extends ConsoleParser{ ... }

val myInt = ConsoleParseInt.parse()
val myBoolean = ConsoleParseBoolean.parse()
val myDouble = ConsoleParseDouble.parse()
