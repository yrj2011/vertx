# vertx

收藏了一些我在学习Vertx的一些小demo，具体的说明可以参考：<a href="https://blog.csdn.net/king_kgh/article/details/80772657">https://blog.csdn.net/king_kgh/article/details/80772657</a>

## 目录说明

* com.stu.vertx.hello

这个包下是使用Vertx创建的一个最基础的HttpServer，直接运行即可。通过浏览器访问 http://localhost:8888/ 即可

* com.stu.vertx.route

这个包是路由相关操作的展示，包括基本路径匹配，请求方法限制，REST风格实现，二级路由等功能

* com.stu.vertx.timer

这个包主要演示计时相关的操作，比如延时执行一些操作或者每隔一段时间执行一些操作。

* com.stu.vertx.eventbus

使用消息总线实现基本的发布，订阅模型

* com.stu.vertx.cluster.service

这里模拟一个完整的微服务的架构，每个Verticle可以做为单独的部署单元，服务间会互相发现，通过EventBus进行通信

FirstVerticle是服务的提供者，提供一个sayHello方法

ListenerVerticle监听浏览器的请求，然后通过EventBus调用服务，并相应数据



