# vertx

收藏了一些我在学习Vertx的一些小demo，具体的说明可以参考：<a href="https://blog.csdn.net/king_kgh/article/details/80772657">https://blog.csdn.net/king_kgh/article/details/80772657</a>

## 目录说明

* com.stu.vertx.core.httpserver

这个包下是使用Vertx创建的一个最基础的HttpServer，直接运行即可。通过浏览器访问 http://localhost:8888/ 即可

* com.stu.vertx.web.route

这个包是路由相关操作的展示，包括基本路径匹配，请求方法限制，REST风格实现，二级路由等功能

* com.stu.vertx.core.timer

这个包主要演示计时相关的操作，比如延时执行一些操作或者每隔一段时间执行一些操作。

* com.stu.vertx.core.eventbus

使用消息总线实现基本的发布，订阅模型

* com.stu.vertx.cluster.service

这里模拟一个完整的微服务的架构，每个Verticle可以做为单独的部署单元，服务间会互相发现，通过EventBus进行通信

FirstVerticle是服务的提供者，提供一个sayHello方法。每一个独立的Verticle都应该具备三个类，一个是用于启动的Verticle，这里就是FirstVerticle；需要一个对外暴露的接口，这里就是Service；还需要一个接口的实现类，这里就是ServiceImpl。

ListenerVerticle监听浏览器的请求，然后通过EventBus调用服务，并响应数据。

* com.stu.vertx.jdbc

这里主要演示的是vertx整合jdbc，操作数据库。因为Vertx是异步框架，如果选择使用Vertx就不能使用类似于Mybatis，Hibernate等优秀的开源ORM框架，因为他们是同步的，这样会导致Vertx阻塞，会严重影响Vertx的性能。

* com.stu.core.cache

Vertx提供了对缓存的支持，可以使用单独的管理功能在运行期清理指定缓存。

## 常见问题

* ServiceVertxEBProxy 这个类找不到，开发工具提示错误

这个类是通过Vertx的CodeGenerate生成的代理类，需要使用mvn install来触发代码生成工具来生成此类。如果执行了mvn install之后依然没有，可以按以下步骤检验：

1.在pom.xml中进行配置，如下：

```xml
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-compiler-plugin</artifactId>
	<version>3.1</version>
	<configuration>
		<source>1.8</source>
		<target>1.8</target>
		<encoding>UTF-8</encoding>
		<annotationProcessors>
			<annotationProcessor>io.vertx.codegen.CodeGenProcessor</annotationProcessor>
		</annotationProcessors>
		<generatedSourcesDirectory>
			${project.basedir}/src/main/generated
		</generatedSourcesDirectory>
		<compilerArgs>
			<arg>-AoutputDirectory=${project.basedir}/src/main</arg>
		</compilerArgs>
	</configuration>
</plugin>
```

我所配置的路径在 src/main/generated下，如果使用eclipse，可以将这个路径配置到classpath下。

2.检查Service接口是否使用注解

```java
@ProxyGen
@VertxGen
```

3. 检查package-info.java是否正确

```java
@ModuleGen(groupPackage = "com.stu.vertx.cluster.service.hello", name = "FirstVerticle")
package com.stu.vertx.cluster.service.hello;

import io.vertx.codegen.annotations.ModuleGen;
```




