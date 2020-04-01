// app/src/MinimalApplication.scala
var socket = new WebSocket("ws://" + location.host + "/subscribe");
socket.onmessage = function(ev){ messageList.innerHTML = ev.data }
