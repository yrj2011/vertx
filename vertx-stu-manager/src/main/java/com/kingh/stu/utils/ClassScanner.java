package com.kingh.stu.utils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 类扫描工具类
 *
 * @author 孔冠华
 */
public class ClassScanner {

    private List<Class<?>> classList = new ArrayList<>();

    private static volatile ClassScanner instance;

    private ClassScanner() {
        scanner("");
    }

    public static ClassScanner getInstance() {
        if (instance == null) {
            synchronized (ClassScanner.class) {
                if (instance == null) {
                    instance = new ClassScanner();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) throws Exception {
        new ClassScanner().scanner("");
    }

    /**
     * 获取标有注解的类
     *
     * @param anno
     * @return
     */
    public List<Class> getAnnoClass(Class anno) {
        List<Class> selectClass = new ArrayList<>();
        for (Class clazz : classList) {
            Annotation[] annos = clazz.getAnnotations();
            if (annos != null && annos.length > 0) {
                for (Annotation ann : annos) {
                    if (ann.annotationType() == anno) {
                        selectClass.add(clazz);
                        break;
                    }
                }
            }
        }
        return selectClass;
    }

    private ClassLoader loader;

    /**
     * 扫描指定包下的所有类
     *
     * @param basePackage
     */
    public void scanner(String basePackage) {
        loader = ClassScanner.class.getClassLoader();

        try {
            Enumeration<URL> r = loader.getResources(basePackage);
            while (r.hasMoreElements()) {
                URL url = r.nextElement();
                File file = new File(url.getFile());
                foreachfile(file);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 遍历文件
     *
     * @param file
     * @throws Exception
     */
    private void foreachfile(File file) throws Exception {
        if (file.isDirectory()) {
            File[] fs = file.listFiles();
            for (File f : fs) {
                foreachfile(f);
            }
        } else {
            String path = file.getAbsolutePath();
            if (path.endsWith(".class")) {
                System.out.println("class path is " + path);
                String fullClassName = path.substring(path.indexOf("classes") + 8, path.length() - 6).replaceAll("\\\\",
                        ".").replaceAll("/",".");
                System.out.println("class name is " + fullClassName);
                Class clazz = loader.loadClass(fullClassName);
                classList.add(clazz);
            }
        }
    }

}
