// parsed
Expr.Local(
  "f",
  Expr.Func(
    List("a"),
    Expr.Plus(List(
      Expr.Ident("a"),
      Expr.Str("1")
    ))
  ),
  Expr.Call(
    Expr.Ident("f"),
    List(Expr.Str("123"))
  )
)
