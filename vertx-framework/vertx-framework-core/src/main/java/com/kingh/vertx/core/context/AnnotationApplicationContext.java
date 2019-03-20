package com.kingh.vertx.core.context;

import com.kingh.vertx.common.anno.Param;
import com.kingh.vertx.common.anno.Service;
import com.kingh.vertx.common.anno.Verticle;
import com.kingh.vertx.common.bean.ServiceBean;
import com.kingh.vertx.common.bean.VerticleBean;
import com.kingh.vertx.common.constant.Status;
import com.kingh.vertx.common.scan.Scanner;
import com.kingh.vertx.core.service.CoreService;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/19 9:58
 */
public class AnnotationApplicationContext implements ApplicationContext {

    // 所有扫描出的类
    private Set<Class<?>> classes;

    // 系统运行的所有组件
    private Map<String, VerticleBean> verticles = new HashMap<>();

    // 核心服务
    private CoreService coreService;

    // Vertx实例
    private Vertx vertx;


    public AnnotationApplicationContext() {
        this.vertx = Vertx.vertx();
        this.coreService = CoreService.create(vertx);
    }

    @Override
    public void run() {
        // 扫描所有的类
        classes = Scanner.scanner("");

        // 分析类
        List<Class> verticles = classes.stream()
                .filter(c -> c.isAnnotationPresent(Verticle.class))
                .collect(Collectors.toList());

        // 构建链
        buildChain(verticles);

        // 部署自动服务
        this.verticles
                .values()
                .stream()
                .filter(VerticleBean::isAutoDeploy)
                .forEach(v -> {
                    coreService.deployVerticle(v, res -> {
                    });
                });
    }

    /**
     * 构建链
     *
     * @param verticles
     */
    private void buildChain(List<Class> verticles) {
        if (verticles == null || verticles.size() == 0) {
            return;
        }
        verticles.stream()
                .forEach(c -> {

                    // 组建Verticle
                    Verticle vert = (Verticle) c.getAnnotation(Verticle.class);
                    VerticleBean verticleBean = new VerticleBean();
                    verticleBean.setAddress(vert.address());
                    verticleBean.setAutoDeploy(vert.autoDeploy());
                    verticleBean.setName(vert.name());
                    verticleBean.setDescription(vert.description());
                    verticleBean.setStatus(Status.not_available);
                    try {
                        verticleBean.setVerticle((io.vertx.core.Verticle) c.newInstance());
                    } catch (Exception e) {
                        throw new RuntimeException("实例化Verticle失败，" + c);
                    }
                    DeploymentOptions options = new DeploymentOptions()
                            .setHa(vert.ha())
                            .setInstances(vert.instances())
                            .setWorker(vert.worker())
                            .setMultiThreaded(vert.multiThreaded())
                            .setIsolationGroup(vert.isolationGroup())
                            .setWorkerPoolSize(vert.workerPoolSize())
                            .setWorkerPoolName(vert.workerPoolName())
                            .setMaxWorkerExecuteTime(vert.maxWorkerExecuteTime());
                    verticleBean.setDeploymentOptions(options);

                    // 组装服务
                    Class service = vert.service();
                    // 获取这个类的所有的公有接口方法
                    Method[] methods = service.getMethods();
                    Set<ServiceBean> serviceBeans = Arrays.stream(methods)
                            .filter(m -> m.isAnnotationPresent(Service.class))
                            .map(m -> {
                                Service s = m.getAnnotation(Service.class);
                                ServiceBean serviceBean = new ServiceBean();
                                serviceBean.setName(s.name());
                                serviceBean.setDescription(s.description());
                                serviceBean.setId(m.getName());

                                Map<String, ServiceBean.Param> params = new HashMap<>();
                                Arrays.stream(m.getParameters())
                                        .filter(parameter -> parameter.isAnnotationPresent(Param.class))
                                        .forEach(parameter -> {
                                            Param p = parameter.getAnnotation(Param.class);
                                            ServiceBean.Param pa = new ServiceBean.Param();
                                            pa.setDescription(p.description());
                                            pa.setLength(p.length());
                                            pa.setMust(p.must());
                                            pa.setTypeName(parameter.getType().getName());
                                            pa.setName(p.name());
                                            params.put(pa.getName(), pa);
                                        });
                                serviceBean.setParams(params);
                                return serviceBean;
                            })
                            .collect(Collectors.toSet());
                    verticleBean.setServices(serviceBeans);
                    this.verticles.put(vert.name(), verticleBean);
                });
    }
}
