// 引入依赖的包
const http = require("http");

// 创建HTTP服务
var server = http.createServer();

server.on("request", function(req, resp) {
    // 设置响应头
    resp.setHeader("Content-Type", "text/html;charset=utf-8");
    resp.end("Hello World !");
});

// 监听请求，并在启动成功之后，打印Server run Success
server.listen(8080, () => {
    console.log("Server Run Success.")
});