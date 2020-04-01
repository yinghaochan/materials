// build.sc
import $ivy.`com.lihaoyi::scalatags:0.8.4`, scalatags.Text.all._
import $ivy.`com.atlassian.commonmark:commonmark:0.13.1`
import mill._

interp.watch(os.pwd / "post")
val postInfo = os
  .list(os.pwd / "post")
  .map{ p =>
    val Array(prefix, suffix) = p.last.split(" - ")
    (prefix, suffix.stripSuffix(".md"), p)
  }
  .sortBy(_._1.toInt)

def mdNameToHtml(name: String) = name.stripSuffix(".md").replace(" ", "-").toLowerCase + ".html"

def bootstrap = T{
  os.write(
    T.dest / "bootstrap.min.css",
    requests.get("https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css").text()
  )
  PathRef(T.dest / "bootstrap.min.css")
}

object post extends Cross[PostModule](postInfo.map(_._1):_*)
class PostModule(number: String) extends Module{
  val Some((_, suffix, path)) = postInfo.find(_._1 == number)
  def srcPath = T.source(path)
  def renderMarkdown(s: String) = {
    val parser = org.commonmark.parser.Parser.builder().build()
    val document = parser.parse(s)
    val renderer = org.commonmark.renderer.html.HtmlRenderer.builder().build()
    renderer.render(document)
  }
  def preview = T{
    val firstPara = os.read.lines(srcPath().path).takeWhile(_.nonEmpty)
    renderMarkdown(firstPara.mkString("\n"))
  }
  def render = T{
    val output = renderMarkdown(os.read(srcPath().path))
    os.write(
      T.dest /  mdNameToHtml(suffix),
      doctype("html")(
        html(
          head(head(link(rel := "stylesheet", href := "../bootstrap.min.css"))),
          body(
            h1(a("Blog", href := "../index.html"), " / ", suffix.stripSuffix(".md")),
            raw(output)
          )
        )
      )
    )
    PathRef(T.dest / mdNameToHtml(suffix))
  }
}

val previews = T.sequence(post.itemMap.values.map(_.preview).toSeq)

def links = T.input(postInfo.map(_._2))

def index = T{
  os.write(
    T.dest / "index.html",
    doctype("html")(
      html(
        head(head(link(rel := "stylesheet", href := "bootstrap.min.css"))),
        body(
          h1("Blog"),
          for ((suffix, preview) <- links().zip(previews()))
          yield frag(h2(a(suffix, href := ("post/" + mdNameToHtml(suffix)))), raw(preview))
        )
      )
    )
  )
  PathRef(T.dest / "index.html")
}

val posts = T.sequence(postInfo.map(_._1).map(post(_).render))

def dist = T {
  for (post <- posts()) {
    os.copy(post.path, T.dest / "post" / post.path.last, createFolders = true)
  }
  os.copy(bootstrap().path, T.dest / "bootstrap.min.css")
  os.copy(index().path, T.dest / "index.html")
  PathRef(T.dest)
}
