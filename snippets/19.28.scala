@ {
  def prefix[_: P] = P( "hello" | "goodbye" ).!.map(Word)
  def suffix[_: P] = P( "world" | "seattle" ).!.map(Word)
  def ws[_: P] = P( " ".rep(1) )
  def parened[_: P] = P( "(" ~ parser ~ ")" )
  def parser[_: P]: P[Phrase] = P( (parened | prefix) ~ ws ~ (parened | suffix) ).map{
    case (lhs, rhs) => Pair(lhs, rhs)
  }
  }
