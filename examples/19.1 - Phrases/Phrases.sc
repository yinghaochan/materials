// Phrases.sc
sealed trait Phrase
case class Word(s: String) extends Phrase
case class Pair(lhs: Phrase, rhs: Phrase) extends Phrase

import fastparse._, NoWhitespace._
def prefix[_: P] = P( "hello" | "goodbye" ).!.map(Word)
def suffix[_: P] = P( "world" | "seattle" ).!.map(Word)
def ws[_: P] = P( " ".rep(1) )
def parened[_: P] = P( "(" ~ parser ~ ")" )
def parser[_: P]: P[Phrase] = P( (parened | prefix) ~ ws ~ (parened | suffix) ).map{
  case (lhs, rhs) => Pair(lhs, rhs)
}
// Usage
assert(
  pprint.log(fastparse.parse("hello seattle", parser(_))) ==
  Parsed.Success(value = Pair(Word("hello"), Word("seattle")), index = 13)
)
assert(
  pprint.log(fastparse.parse("hello (goodbye seattle)", parser(_))) ==
  Parsed.Success(
    value = Pair(Word("hello"), Pair(Word("goodbye"), Word("seattle"))),
    index = 23
  )
)
assert(
  pprint.log(fastparse.parse("(hello  world)   (goodbye seattle)", parser(_))) ==
  Parsed.Success(
    value = Pair(Pair(Word("hello"), Word("world")), Pair(Word("goodbye"), Word("seattle"))),
    index = 34
  )
)
assert(
  pprint.log(fastparse.parse("(hello  world)   ((goodbye seattle) world)", parser(_))) ==
  Parsed.Success(
    value = Pair(Pair(Word("hello"), Word("world")), Pair(Pair(Word("goodbye"), Word("seattle")), Word("world"))),
    index = 42
  )
)
