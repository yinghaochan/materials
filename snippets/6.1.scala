def breadthFirstSearch[T](start: T, graph: Map[T, Seq[T]]): Set[T] = {
  val seen = collection.mutable.Set(start)
  val queue = collection.mutable.ArrayDeque(start)
  while(queue.nonEmpty){
    val current = queue.removeHead()
    for(next <- graph(current) if !seen(next)){
      seen.add(next)
      queue.append(next)
    }
  }
  seen.toSet
}
