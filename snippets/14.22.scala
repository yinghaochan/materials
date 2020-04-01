// app/src/MinimalApplication.scala
package app
import scalatags.Text.all._
object MinimalApplication extends cask.MainRoutes{
  var messages = Vector(("alice", "Hello World!"), ("bob", "I am cow, hear me moo"))

  @cask.postForm("/")
  def postHello(name: String, msg: String) = {
    if (name == "") hello(Some("Name cannot be empty"), Some(name), Some(msg))
    else if (msg == "") hello(Some("Message cannot be empty"), Some(name), Some(msg))
    else {
      messages = messages :+ (name -> msg)
      hello(None, Some(name), None)
    }
  }

  @cask.get("/")
  def hello(errorOpt: Option[String] = None,
            userName: Option[String] = None,
            msg: Option[String] = None) = doctype("html")(
    html(
      head(
        link(
          rel := "stylesheet",
          href := "https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.css"
        ),
      ),
      body(
        div(cls := "container")(
          h1("Scala Chat!"),
          div(for((name, msg) <- messages) yield p(b(name), " ", msg)),
          for(error <- errorOpt)
          yield i(color.red)(error),
          form(action := "/", method := "post")(
            input(
              `type` := "text",
              name := "name",
              placeholder := "User name",
              userName.map(value := _)
            ),
            input(
              `type` := "text",
              name := "msg",
              placeholder := "Write a message!",
              msg.map(value := _)
            ),
            input(`type` := "submit")
          )
        )
      )
    )
  }

  initialize()
}
