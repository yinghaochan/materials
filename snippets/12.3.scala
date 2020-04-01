val r = requests.get("https://api.github.com/users/lihaoyi")

r.statusCode
// 200

r.headers("content-type")
// Buffer("application/json; charset=utf-8")

r.text
// {"login":"lihaoyi","id":934140,"node_id":"MDQ6VXNlcjkzNDE0MA==",...
