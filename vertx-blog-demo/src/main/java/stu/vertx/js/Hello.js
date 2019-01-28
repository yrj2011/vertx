var server = vertx.createHttpServer();

server.requestHandler(function (request) {
    console.log("Hello")
    request.response().end("Hello world");
});

server.listen(8080);