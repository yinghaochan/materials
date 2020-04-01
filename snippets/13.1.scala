val futures = for(p <- os.list(os.pwd)) yield Future{
  println(p)
  hash(p.last)
}
val hashes = futures.map(Await.result(_, Duration.Inf))
