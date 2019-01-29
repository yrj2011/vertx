// 创建HTTP服务
var server = vertx.createHttpServer();

// 指定处理器处理客户端连接
server.requestHandler(function (request) {
    request.response().end("Hello World！");
});

// 监听客户端的连接
server.listen(8080);