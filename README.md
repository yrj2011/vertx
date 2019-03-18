# vertx

Vert.x是架构在Netty上的一个异步开发框架，以其小巧、灵活、轻量的特性吸引了一大批的开发者。

这个应用就是我在学习和使用Vert.x的时候逐步积累而来的。包含一些学习的demo以及根据自己体会所编写的Vert.x架构体系。

## 模块说明

这个项目是一个Maven聚合项目，包含很多的小模块，以vertx-开头的模块为Vert.x核心模块，ext-开头的模块为扩展模块，主要是一些在Vert.x模块中用到的技术扩展，这里简单对各小模块进行介绍。

### vertx-blog-demo

这个模块是我编写的部分博客文章中对应的案例代码，包含了Vert.x最为基础的一些案例。

博客的地址为：<a href="https://blog.csdn.net/king_kgh">https://blog.csdn.net/king_kgh</a>

### vertx-book-demo

这个模块是公司内部Vert.x资料中所使用的Demo，但目前还在组建中。

### vertx-framework

这个模块也是一个聚合项目，期望打造一个便捷的Vert.x开发框架，能够快速的开发Web服务，并实时监控Vert.x应用性能。

该模块目前还在需求阶段

### vertx-stu-manager

该模块是一个简单的学生管理系统，能够实现列表、新增和删除。

### ext-thread-demo

一些关于线程的小例子