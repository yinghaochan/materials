// LoggingLongPipeline.sc
import $ivy.`com.lihaoyi::castor:0.1.3`
class Writer(log: os.Path, old: os.Path, rotateSize: Int)
            (implicit cc: castor.Context) extends castor.SimpleActor[String]{
  def run(s: String) = {
    val newLogSize = logSize + s.length + 1
    if (newLogSize <= rotateSize) logSize = newLogSize
    else {
      logSize = s.length
      os.move(log, old, replaceExisting = true)
    }
    os.write.append(log, s + "\n", createFolders = true)
  }
  private var logSize = 0
}
class Uploader(url: String)(implicit cc: castor.Context) extends castor.SimpleActor[String]{
  @volatile var responseCount = 0
  def run(msg: String) = {
    val res = requests.post(url, data = msg)
    responseCount += 1
    println(s"response ${res.statusCode} " + ujson.read(res.text())("data"))
  }
}
class Encoder(dest: castor.Actor[String])
             (implicit cc: castor.Context) extends castor.SimpleActor[String]{
  def run(msg: String) = {
    dest.send(java.util.Base64.getEncoder.encodeToString(msg.getBytes))
  }
}
class Logger(dest: castor.Actor[String])
            (implicit cc: castor.Context) extends castor.SimpleActor[String]{
  def run(msg: String) = {
    dest.send(msg.replaceAll("([0-9]{4})[0-9]{8}([0-9]{4})", "<redacted>"))
  }
}

implicit val cc = new castor.Context.Test()

val writer = new Writer(os.pwd / "log.txt", os.pwd / "log-old.txt", rotateSize = 50)
val uploader = new Uploader("https://httpbin.org/post")
val encoder = new Encoder(new castor.SplitActor(writer, uploader))
val logger = new Logger(encoder)

// Usage

logger.send("I am cow")
logger.send("hear me moo")
logger.send("I weight twice as much as you")
logger.send("And I look good on the barbecue")
logger.send("Yoghurt curds cream cheese and butter")
logger.send("Comes from liquids from my udder")
logger.send("I am cow1234567887654321")
logger.send("Hear me moo, moooo")

cc.waitForInactivity()

assert(pprint.log(os.read(os.pwd / "log-old.txt")) == "Q29tZXMgZnJvbSBsaXF1aWRzIGZyb20gbXkgdWRkZXI=\n")
assert(pprint.log(os.read(os.pwd / "log.txt")) == "SSBhbSBjb3c8cmVkYWN0ZWQ+\nSGVhciBtZSBtb28sIG1vb29v\n")

def decodeFile(p: os.Path) = {
  os.read.lines(p).map(s => new String(java.util.Base64.getDecoder.decode(s)))
}

assert(pprint.log(decodeFile(os.pwd / "log-old.txt")) == Seq("Comes from liquids from my udder"))
assert(pprint.log(decodeFile(os.pwd / "log.txt")) == Seq("I am cow<redacted>", "Hear me moo, moooo"))

assert(uploader.responseCount == 8)
