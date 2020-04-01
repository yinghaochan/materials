@ import $ivy.`com.lihaoyi::os-lib-watch:0.6.3`

@ os.makrDir(os.pwd / "out")

@ os.watch.watch(
    Seq(os.pwd / "out"),
    paths => println("paths changed: " + paths.mkString(", "))
  )

@ os.write(os.pwd / "out" / "i am", "cow")
paths changed: /Users/lihaoyi/test/out/i am

@ os.move(os.pwd / "out"/ "i am", os.pwd / "out" / "hear me")
paths changed: /Users/lihaoyi/test/out/i am,/Users/lihaoyi/test/out/hear me
