package com.kingh.vertx.core.context;

import com.kingh.vertx.common.anno.*;
import com.kingh.vertx.common.bean.ChainBean;
import com.kingh.vertx.common.bean.ServiceBean;
import com.kingh.vertx.common.bean.VerticleBean;
import com.kingh.vertx.common.constant.Status;
import com.kingh.vertx.common.scan.Scanner;
import com.kingh.vertx.core.config.ConfigUtils;
import com.kingh.vertx.core.config.Value;
import com.kingh.vertx.core.service.CoreService;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.Configuration;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/19 9:58
 */
public class AnnotationApplicationContext implements ApplicationContext {

    private Logger logger = LoggerFactory.getLogger(AnnotationApplicationContext.class);

    // 所有扫描出的类
    private Set<Class<?>> classes;

    // 系统运行的所有组件
    private Map<String, VerticleBean> verticles = new HashMap<>();

    // 核心服务
    private CoreService coreService;

    // Vertx实例
    private Vertx vertx;

    // 系统支持的所有链
    private List<ChainBean> chains = new ArrayList<>();

    public AnnotationApplicationContext() {
        this.vertx = this.vertx == null ? Vertx.vertx() : this.vertx;
        this.coreService = CoreService.create(vertx);
        ApplicationContextHolder.setApplicationContext(this);
    }

    @Override
    public void run() {
        long statTime = System.currentTimeMillis();

        // 扫描所有的类
        classes = Scanner.scanner("");

        // 分析类
        List<Class> verticles = classes.stream()
                .filter(c -> c.isAnnotationPresent(Verticle.class))
                .collect(Collectors.toList());

        // 构建基础服务
        buildService(verticles);

        // 构建链
        List<Class> chainConfigurations = classes.stream()
                .filter(c -> c.isAnnotationPresent(ChainConfiguration.class))
                .collect(Collectors.toList());
        buildChain(chainConfigurations, vertx);

        logger.debug("链构建完毕，开始部署自启动组件");

        // 部署自动服务
        this.verticles
                .values()
                .stream()
                .filter(VerticleBean::isAutoDeploy)
                .forEach(v -> {
                    coreService.deployVerticle(v, res -> {
                    });
                });

        logger.debug("系统核心启动成功，耗时 " + (System.currentTimeMillis() - statTime) + " ms");
    }

    /**
     * 构建服务
     *
     * @param verticles
     */
    private void buildService(List<Class> verticles) {
        if (verticles == null || verticles.size() == 0) {
            return;
        }
        logger.info("开始扫描组件及其服务");

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

                    logger.info("开始扫描 " + vert.name() + " 组件的服务");

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
                                serviceBean.setVerticle(verticleBean);

                                logger.debug("扫描到服务名为：" + serviceBean.getName() + " 服务所在类为：" + service.getName());
                                return serviceBean;
                            })
                            .collect(Collectors.toSet());
                    verticleBean.setServices(serviceBeans);
                    this.verticles.put(vert.name(), verticleBean);
                });
    }

    /**
     * 构建链
     *
     * @param chainConfigurations
     */
    private void buildChain(List<Class> chainConfigurations, Vertx vertx) {
        if (chainConfigurations == null || chainConfigurations.size() == 0) {
            return;
        }
        logger.debug("服务扫描完毕，开始构建链");

        // 读取带有@Chain注解的方法，并执行这个方法，拿到链
        chainConfigurations.stream().forEach(c -> {

            // 实例化对象
            Object obj;
            try {
                obj = c.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(c.getName() + " 实例化链配置对象失败");
            }
            Object instance = obj;

            // 获取所有的字段并注入
            Field[] fields = c.getDeclaredFields();
            Arrays.stream(fields).filter(x -> x.isAnnotationPresent(Value.class)).forEach(x -> {
                Value v = x.getAnnotation(Value.class);
                String key = v.key();
                Object value = ConfigUtils.getValue(key, x.getType());
                if (value != null) {
                    try {
                        x.setAccessible(true);
                        x.set(obj, ConfigUtils.getValue(key, x.getType()));
                    } catch (Exception e) {
                        throw new RuntimeException("注入属性失败", e);
                    }
                }
            });

            // 取所有的方法
            Method[] methods = c.getMethods();
            // 遍历标有@Chain的方法
            Arrays.stream(methods)
                    .filter(r -> r.isAnnotationPresent(Chain.class))
                    .forEach(r -> {
                        Chain chainAnno = r.getAnnotation(Chain.class);
                        try {
                            // 链对象
                            Object res = null;

                            // 给这个方法按条件注入 Vertx 实例
                            Class parameterTypes[] = r.getParameterTypes();
                            if (parameterTypes == null || parameterTypes.length == 0) {
                                res = r.invoke(instance);
                            } else if (parameterTypes.length == 1 && parameterTypes[0].getName().contains("Vertx")) {
                                // 目前仅支持注入Vertx对象
                                res = r.invoke(instance, vertx);
                            }
                            if (res == null || !(res instanceof ChainBean)) {
                                // 不处理
                                logger.warn(r.getName() + " 方法返回值为空，或者不为ChainBean对象");
                            } else {
                                ChainBean chain = (ChainBean) res;
                                // 设置链的属性
                                Boolean general = chain.isGeneral();
                                if(general == null) {
                                    chain.setGeneral(chainAnno.general());
                                }
                                Integer pos = chain.getPos();
                                if(pos == null) {
                                    chain.setPos(chainAnno.pos());
                                }
                                String name = chain.getName();
                                if(StringUtils.isBlank(name)) {
                                    chain.setName(chainAnno.name());
                                }
                                Boolean avaiable = chain.isAvaiable();
                                if(avaiable == null) {
                                    chain.setAvaiable(chainAnno.avaiable());
                                }
                                String path = chain.getPath();
                                if(StringUtils.isBlank(path)) {
                                    chain.setPath(chainAnno.path());
                                }
                                HttpMethod[] methods1 = chain.getMethods();
                                if(methods1 == null || methods1.length == 0) {
                                    chain.setMethod(chainAnno.methods());
                                }
                                String desc = chain.getDescription();
                                if(StringUtils.isBlank(desc)) {
                                    chain.setDescription(chainAnno.description());
                                }
//                                chain.setGeneral(chainAnno.general())
//                                        .setPos(chainAnno.pos())
//                                        .setName(chainAnno.name())
//                                        .setAvaiable(chainAnno.avaiable())
//                                        .setPath(chainAnno.path())
//                                        .setMethod(chainAnno.methods())
//                                        .setDescription(chainAnno.description());
                                logger.debug("映射地址为:" + chain.getPath() + " 链定义类为： " + chain.getName() + " 链执行的服务为： " + chain.getServices());
                                chains.add(chain);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new RuntimeException(r.getName() + " 解析链方法失败");
                        }
                    });
        });
    }

    @Override
    public ServiceBean service(String tag) {
        if (tag == null) {
            return null;
        }
        String[] prefix = tag.split(":");
        if (prefix == null || prefix.length != 2) {
            throw new RuntimeException("标识符无效");
        }

        return verticles.get(prefix[0])
                .getServices()
                .stream()
                .filter(r -> r.getName().equals(prefix[1]))
                .findFirst()
                .get();
    }

    @Override
    public void setVertx(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public Vertx vertx() {
        return this.vertx;
    }

    @Override
    public List<ChainBean> chains() {
        return chains;
    }

}
