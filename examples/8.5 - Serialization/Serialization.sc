// Serialization.sc
val jsonString = os.read(os.pwd / "ammonite-releases.json")

val data = ujson.read(jsonString)

println(ujson.write(data(0)("author"), indent=4))

case class Author(login: String, id: Int, site_admin: Boolean)

implicit val authorRw = upickle.default.macroRW[Author]

val author = upickle.default.read[Author](data(0)("author"))

assert(pprint.log(author) == Author("Ammonite-Bot", 20607116, false))

assert(pprint.log(author.login) == "Ammonite-Bot")
assert(pprint.log(author.id) == 20607116)
assert(pprint.log(author.site_admin) == false)

assert(
  pprint.log(upickle.default.write(author)) ==
    """{"login":"Ammonite-Bot","id":20607116,"site_admin":false}"""
)
