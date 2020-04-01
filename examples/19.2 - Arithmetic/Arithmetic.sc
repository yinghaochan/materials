// Arithmetic.sc
import fastparse._, NoWhitespace._

def number[_: P] = P(
  "zero" | "one" | "two" | "three" | "four" |
    "five" | "six" | "seven" | "eight" | "nine"
).!.map{
  case "zero"  => 0
  case "one"   => 1
  case "two"   => 2
  case "three" => 3
  case "four"  => 4
  case "five"  => 5
  case "six"   => 6
  case "seven" => 7
  case "eight" => 8
  case "nine"  => 9
}

def ws[_: P] = P( " ".rep(1) )

def operator[_: P] = P( "plus" | "minus" | "times" | "divide" ).!

def expr[_: P] = P( "(" ~ parser ~ ")" | number )

def parser[_: P]: P[Int] = P( expr ~ ws ~ operator ~ ws ~ expr ).map{
  case (lhs, "plus", rhs) => lhs + rhs
  case (lhs, "minus", rhs) => lhs - rhs
  case (lhs, "times", rhs) => lhs * rhs
  case (lhs, "divide", rhs) => lhs / rhs
}
// Usage
assert(
  pprint.log(fastparse.parse("three times seven", parser(_))) ==
  Parsed.Success(value = 21, index = 17)
)
assert(
  pprint.log(fastparse.parse("(eight divide two) times (nine minus four)", parser(_))) ==
  Parsed.Success(value = 20, index = 42)
)
assert(
  pprint.log(fastparse.parse("five times ((nine times eight) minus four)", parser(_))) ==
  Parsed.Success(value = 340, index = 42)
)
