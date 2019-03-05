package com.kingh.stu.router;

import com.kingh.stu.anno.RequestMapping;
import com.kingh.stu.result.Result;
import com.kingh.stu.result.ResultHandler;
import com.kingh.stu.utils.ClassScanner;
import com.kingh.stu.utils.JdbcUtils;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.web.RoutingContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/4 19:38
 */
public class MainRouterImpl implements MainRouter {

    private Vertx vertx;
    private JDBCClient jdbcClient;
    private Map<String, Method> methodMap = new HashMap<>();
    private Map<Class, Object> classPool = new ConcurrentHashMap<>();

    private String e_404 = "/404.html";
    private String e_500 = "/500.html";

    public MainRouterImpl(Vertx vertx) {
        this.vertx = vertx;
        this.jdbcClient = new JdbcUtils(vertx).getDbClient();

        // 初始化method
        List<Class> classes = ClassScanner.getInstance().getAnnoClass(RequestMapping.class);
        for (Class clazz : classes) {
            Annotation requestMapping = clazz.getAnnotation(RequestMapping.class);
            if (requestMapping instanceof RequestMapping) {
                RequestMapping rm = (RequestMapping) requestMapping;
                String[] classMapping = rm.value();

                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    Annotation annos = method.getAnnotation(RequestMapping.class);
                    if (annos != null) {
                        RequestMapping mapping = (RequestMapping) annos;
                        String[] methodMapping = mapping.value();
                        for (String ns : classMapping) {
                            for (String path : methodMapping) {
                                String handlePath = joinPath(ns, path);
                                if (methodMap.containsKey(handlePath)) {
                                    throw new RuntimeException("请求地址为：" + handlePath + "; 这个路径绑定了多个方法，请检查RequestMapping 配置是否正确。");
                                }
                                methodMap.put(handlePath, method);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void handle(RoutingContext event) {
        String path = event.request().path();
        System.out.println("请求的路径为：" + path);

        Method method = methodMap.get(path);
        if (method == null) {
            event.reroute(e_404);
            return;
        }

        Class clazz = method.getDeclaringClass();
        Object instance = null;
        if (classPool.containsKey(clazz)) {
            instance = classPool.get(clazz);
        } else {
            try {
                instance = clazz.newInstance();
                classPool.put(clazz, instance);
            } catch (Exception e) {
                e.printStackTrace();
                event.reroute(E500);
                return;
            }
        }
        try {
            Type[] types = method.getGenericParameterTypes();
            Object[] params = new Object[types.length];
            for (int i = 0; i < params.length; i++) {
                Type type = types[i];
                String typeName = type.getTypeName();
                if ("io.vertx.ext.web.RoutingContext".equals(typeName)) {
                    params[i] = event;
                } else if ("io.vertx.core.Vertx".equals(typeName)) {
                    params[i] = vertx;
                } else if("io.vertx.ext.jdbc.JDBCClient".equals(typeName)) {
                    params[i] = jdbcClient;
                } else if("io.vertx.core.Handler<io.vertx.core.AsyncResult<com.kingh.stu.result.Result>>".equals(typeName)) {
                    params[i] = new ResultHandler(event);
                }
            }
            method.invoke(instance, params);
        } catch (Exception e) {
            e.printStackTrace();
            event.reroute(e_500);
        }
    }

    /**
     * /user  /save
     * /user  save
     * /user/ /user
     * user  /save
     * user /save/
     *
     * @param namespace
     * @param path
     * @return
     */
    private String joinPath(String namespace, String path) {
        if (namespace == null) {
            namespace = "";
        }

        if (path == null) {
            path = "";
        }

        namespace = namespace.indexOf("/") == 0 ? namespace : "/" + namespace;
        namespace = namespace.lastIndexOf("/") == namespace.length() ? namespace.substring(0, namespace.length() - 1) : namespace;

        path = path.indexOf("/") == 0 ? path : "/" + path;
        path = path.lastIndexOf("/") == path.length() ? path.substring(0, path.length() - 1) : path;

        String res = namespace + path;
        if ("//".equals(res)) {
            res = "/";
        }
        return res;
    }

    @Override
    public void setE404(String e404) {
        this.e_404 = e404;
    }

    @Override
    public void setE500(String e500) {
        this.e_500 = e500;
    }
}
