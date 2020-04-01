@ os.write(src / "ABC.txt", "Hello World")

@ sync(os.pwd / "post", os.pwd / "post-copy")

@ os.exists(dest / "ABC.txt")
res42: Boolean = true

@ os.read(dest / "ABC.txt")
res43: String = "Hello World"
