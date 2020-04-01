// Blog.sc
val postInfo = os
  .list(os.pwd / "post")
  .flatMap{ p =>
    p.last.split(" - ") match{
      case Array(prefix, suffix) => Some((prefix, suffix.stripSuffix(".md"), p))
      case _ => None
    }
  }
  .sortBy(_._1.toInt)

// Usage
assert(
  pprint.log(postInfo.map(t => (t._1, t._2))) ==
  Seq("1" -> "My First Post", "2" -> "My Second Post", "3" -> "My Third Post")
)