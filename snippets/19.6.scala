fastparse.parse("goodbye", parser(_)) match{
  case Parsed.Success(value, index) => ???
  case Parsed.Failure(expected, index, extra) => ???
}
