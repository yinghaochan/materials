// parsed
Expr.Func(
  Seq("a", "b"),
  Expr.Plus(Seq(
    Expr.Ident("a"),
    Expr.Str(" "),
    Expr.Ident("b")
  ))
)
