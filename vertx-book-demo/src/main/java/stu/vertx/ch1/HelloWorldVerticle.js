// 创建HTTP服务
const server = vertx.createHttpServer();

// 指定处理器处理客户端连接
server.requestHandler(request => {
    request.response().end("Hello");
});

// 监听客户端的连接
server.listen(8080);