// Trie.scala
class Trie(){
  class Node(var hasValue: Boolean, val children: collection.mutable.Map[Char, Node] = collection.mutable.Map())
  val root = new Node(false)
  def add(s: String) = {
    var current = root
    for(c <- s) {
      current = current.children.getOrElseUpdate(c, new Node(false))
    }
    current.hasValue = true
  }
  def contains(s: String): Boolean = {
    var current = Option(root)
    for(c <- s if current.nonEmpty) current = current.get.children.get(c)
    current.exists(_.hasValue)
  }
  def prefixesMatchingString0(s: String): Seq[Int] = {
    var current = Option(root)
    val output = Seq.newBuilder[Int]
    for((c, i) <- s.zipWithIndex if current.nonEmpty){
      if (current.get.hasValue) output += i
      current = current.get.children.get(c)
    }
    if (current.exists(_.hasValue)) output += s.length
    output.result()
  }
  def prefixesMatchingString(s: String): Seq[String] = {
    prefixesMatchingString0(s).map(s.substring(0, _))
  }
  def stringsMatchingPrefix(s: String): Seq[String] = {
    var current = Option(root)
    for(c <- s if current.nonEmpty){
      current = current.get.children.get(c)
    }
    if (current.isEmpty) Nil
    else{
      val output = Seq.newBuilder[String]
      def recurse(current: Node, path: List[Char]): Unit = {
        if (current.hasValue) output += (s + path.reverse.mkString)
        for((c, n) <- current.children) recurse(n, c :: path)
      }
      recurse(current.get, Nil)
      output.result()
    }
  }
}

// Usage
val t = new Trie()

t.add("mango")
t.add("mandarin")
t.add("map")
t.add("man")

assert(pprint.log(t.contains("mango")) == true)

assert(pprint.log(t.contains("mang")) == false)

assert(pprint.log(t.contains("man")) == true)

assert(pprint.log(t.contains("mandarin")) == true)

assert(pprint.log(t.contains("mandarine")) == false)

assert(pprint.log(t.prefixesMatchingString("mangosteen")) == List("man", "mango"))

assert(pprint.log(t.stringsMatchingPrefix("man")) == List("man", "mandarin", "mango"))

assert(pprint.log(t.stringsMatchingPrefix("ma")) == List("map", "man", "mandarin", "mango"))

assert(pprint.log(t.stringsMatchingPrefix("map")) == List("map"))

assert(pprint.log(t.stringsMatchingPrefix("mand")) == List("mandarin"))

assert(pprint.log(t.stringsMatchingPrefix("mando")) == List())