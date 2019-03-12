package com.kingh.vertx.core;

import com.kingh.vertx.core.anno.Action;
import com.kingh.vertx.core.anno.Chain;
import com.kingh.vertx.core.anno.VerticleCompoment;
import com.kingh.vertx.core.node.Compoment;
import com.kingh.vertx.core.node.StaticChain;
import com.kingh.vertx.core.node.StaticNode;
import com.kingh.vertx.core.service.CoreService;
import com.kingh.vertx.core.utils.ClassScanner;
import io.vertx.core.Context;
import io.vertx.core.Vertx;

import java.util.List;

/**
 * 核心启动类
 *
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/11 16:44
 */
public class VertxApplication {

    // 组件 （包含服务）
    private List<Compoment> compoments;

    // 服务 （最小执行单元）
    private List<StaticNode> staticNodes;

    // 功能 （多个组件构成一个功能）
    private List<StaticChain> staticChains;


    public void start() {

        // 扫描类，封装服务和链
        ClassScanner scanner = ClassScanner.getInstance();
        scanner.scanner("");
        List<Class> nodes = scanner.getAnnoClass(Action.class);
        List<Class> chains = scanner.getAnnoClass(Chain.class);
        List<Class> verticles = scanner.getAnnoClass(VerticleCompoment.class);

        System.out.println(verticles);
        System.out.println(chains);
        System.out.println(nodes);

        // 创建实例
        Vertx vertx = Vertx.vertx();
        Context context = vertx.getOrCreateContext();
        context.put("compoments", compoments);
        context.put("staticNodes", staticNodes);
        context.put("staticChains", staticChains);

        // 部署自启动服务和链
        CoreService service = CoreService.create(vertx);
        compoments.stream().filter(c -> c.isAutoDeploy()).forEach(r -> service.deployService(r.getName(), res -> {
            if (res.succeeded()) {
                System.out.println(res.result().getName() + " 服务部署成功");
            } else {
                System.out.println("服务部署失败," + res
                        .cause().getMessage());
            }
        }));


    }

    public static void main(String[] args) {
        new VertxApplication().start();
    }


}
