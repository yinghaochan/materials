@ os.write.append(src / "ABC.txt", "\nI am Cow")

@ sync(os.pwd / "post", os.pwd / "post-copy")

@ os.read(dest / "ABC.txt")
res46: String = """Hello World
I am Cow"""
