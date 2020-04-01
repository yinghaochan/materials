// app/src/MinimalApplication.scala
+  @cask.postForm("/")
+  def postHello(name: String, msg: String) = {
+    messages = messages :+ (name -> msg)
+    hello()
+  }
   @cask.get("/")
