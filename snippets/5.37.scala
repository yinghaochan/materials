trait StrParser{ def parse(s: String): Int }
object ParseInt extends StrParser{ def parse(s: String): Int = s.toInt }
object ParseBoolean extends StrParser{ def parse(s: String): Int = s.toBoolean }
object ParseDouble extends StrParser{ def parse(s: String): Int = s.toDouble }
