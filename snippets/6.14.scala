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
