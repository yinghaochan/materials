// sync/src/Sync.scala
- def callAgent[T: upickle.default.Reader](Rpc: Rpc): T = {
+ def callAgent[T: upickle.default.Reader](Rpc: Rpc): () => T = {
   Shared.send(agent.stdin.data, rpc)
-  Shared.receive[T](agent.stdout.data)
+  () => Shared.receive[T](agent.stdout.data)
 }
