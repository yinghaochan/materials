@ println(output)
[{"hello":"goodbye","answer":42,"tags":["awesome","yay","wonderful"]},true]

@ output(0).obj.remove("hello")

@ println(output)
[{"answer":42,"tags":["awesome","yay","wonderful"]},true]

@ output.arr.append(123)

@ println(output)
[{"answer":42,"tags":["awesome","yay","wonderful"]},true,123]
