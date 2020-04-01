// build.sc
  def links = T.input{ postInfo.map(_._2) }
+ val previews = T.sequence(post.itemMap.values.map(_.preview).toSeq)
  def index = T{
