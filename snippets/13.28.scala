def futureFunc(): Future[String] = { ... }

def callbackFunc(onSuccess: String => Unit, onError: Throwable => Unit): Unit = {
  futureFunc().onComplete{
    case scala.util.Success(str) => callback(str)
    case scala.util.Failure(ex) => callback(ex)
  }
}
