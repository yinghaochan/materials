// async
val files = Seq(
  "Chinatown.jpg", "ZCenter.jpg",
  "Kresge.jpg", "Memorial.jpg"
)
val futs = files.map(s => Future{hash(s)})
val combined = Future.sequence(futs)
val joined = combined.map(_.mkString(" "))
joined.foreach(println)
