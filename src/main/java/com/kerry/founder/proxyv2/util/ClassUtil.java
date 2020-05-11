package com.kerry.founder.proxyv2.util;

import com.kerry.founder.proxyv2.annotation.AfterAnnotation;
import com.kerry.founder.proxyv2.annotation.BeforeAnnotation;
import com.kerry.founder.proxyv2.aspect.MyCustomAspect;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * **********书山有路勤为径**********
 *
 * @author k1rry
 * @date 2020/5/11
 * **********学海无涯苦作舟**********
 */
public class ClassUtil {

    private ClassUtil() { }

    /**
     * 从包package中获取所有的Class
     *
     * @param packageName
     * @return
     */
    public static List<Class<?>> getClasses(String packageName) {
        // 第一个class类的集合
        List<Class<?>> classes = new ArrayList<Class<?>>();
        // 是否循环迭代
        boolean recursive = true;
        // 获取包的名字 并进行替换
        String packageDirName = packageName.replace('.', '/');
        // 定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            // 循环迭代下去
            while (dirs.hasMoreElements()) {
                // 获取下一个元素
                URL url = dirs.nextElement();
                // 得到协议的名称
                String protocol = url.getProtocol();
                // 如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)) {
                    // 获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    // 以文件的方式扫描整个包下的文件 并添加到集合中
                    findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
                } else if ("jar".equals(protocol)) {
                    // 如果是jar包文件
                    // 定义一个JarFile
                    JarFile jar;
                    try {
                        // 获取jar
                        jar = ((JarURLConnection) url.openConnection()).getJarFile();
                        // 从此jar包 得到一个枚举类
                        Enumeration<JarEntry> entries = jar.entries();
                        // 同样的进行循环迭代
                        while (entries.hasMoreElements()) {
                            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                            JarEntry entry = entries.nextElement();
                            String name = entry.getName();
                            // 如果是以/开头的
                            if (name.charAt(0) == '/') {
                                // 获取后面的字符串
                                name = name.substring(1);
                            }
                            // 如果前半部分和定义的包名相同
                            if (name.startsWith(packageDirName)) {
                                int idx = name.lastIndexOf('/');
                                // 如果以"/"结尾 是一个包
                                if (idx != -1) {
                                    // 获取包名 把"/"替换成"."
                                    packageName = name.substring(0, idx).replace('/', '.');
                                }
                                // 如果可以迭代下去 并且是一个包
                                if ((idx != -1) || recursive) {
                                    // 如果是一个.class文件 而且不是目录
                                    if (name.endsWith(".class") && !entry.isDirectory()) {
                                        // 去掉后面的".class" 获取真正的类名
                                        String className = name.substring(packageName.length() + 1, name.length() - 6);
                                        try {
                                            // 添加到classes
                                            classes.add(Class.forName(packageName + '.' + className));
                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }

    /**
     * 以文件的形式来获取包下的所有Class
     * @param packageName
     * @param packagePath
     * @param recursive
     * @param classes
     */
    private static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, List<Class<?>> classes){
        //获取此包的目录 建立一个File
        File dir = new File(packagePath);
        //如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        //如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = dir.listFiles(new FileFilter() {
            //自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
            @Override
            public boolean accept(File file) {
                return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
            }
        });
        //循环所有文件
        for (File file : dirfiles) {
            //如果是目录 则继续扫描
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(),
                        file.getAbsolutePath(), recursive, classes);
            }
            else {
                //如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    //添加到集合中去
                    classes.add(Class.forName(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通过切面类获取切点方法
     * @param customAspect
     * @return
     * @throws ClassNotFoundException
     */
    public static Map<String, Map<String, String>> getMethod(MyCustomAspect customAspect) throws ClassNotFoundException {
        Map<String, Map<String, String>> methodsMap = new HashMap<>();
        for (Method method : customAspect.getClass().getDeclaredMethods()) {
            if (method.getAnnotationsByType(BeforeAnnotation.class) != null) {
                BeforeAnnotation[] beforeAnnotations = method.getAnnotationsByType(BeforeAnnotation.class);
                for (BeforeAnnotation annotation : beforeAnnotations) {
                    getMethod(annotation.value(), methodsMap, "before");
                }
            }

            if (method.getAnnotationsByType(AfterAnnotation.class) != null) {
                AfterAnnotation[] afterAnnotations = method.getAnnotationsByType(AfterAnnotation.class);
                for (AfterAnnotation annotation : afterAnnotations) {
                    getMethod(annotation.value(), methodsMap, "after");
                }
            }
        }
        return methodsMap;
    }

    /**
     * 获取方法
     * @param path
     * @param methodsMap
     * @param key
     * @throws ClassNotFoundException
     */
    private static void getMethod(String path, Map<String, Map<String, String>> methodsMap, String key) throws ClassNotFoundException {
        if (StringUtils.isBlank(path)) {
            throw new InvalidParameterException("path not be null...");
        }
        // path到方法名级别，暂时只考虑类和方法用 (.*)表示所有
        // 如果class用(.*)表示
        Map<String, String> clazzPth = new HashMap<>();
        if (path.indexOf(".*") != path.length()-4) {
            String pkg = path.substring(0, path.indexOf(".*"));
            List<Class<?>> classes = ClassUtil.getClasses(pkg);
            if (!CollectionUtils.isEmpty(classes)) {
                classes.forEach(clazz -> {
                    clazzPth.put(clazz.getName(), path);
                });
            }
        } else {
            clazzPth.put(path, path);
        }
        methodsMap.put(key, clazzPth);
        Map<String, String> methodsPth = new HashMap<>();
        // method 用(.*)表示
        if (path.lastIndexOf(".*") == path.length()-4) {
            for (String className : methodsMap.get(key).keySet()) {
                Class<?> clazz = Class.forName(className);
                Method[] methods = clazz.getDeclaredMethods();
                if (methods != null) {
                    for (Method method : methods) {
                        String pth = className + "." + method.getName();
                        methodsPth.put(pth, path);
                    }
                }
            }
        } else {
            String methodName = path.substring(path.lastIndexOf(".")+1);
            for (String className : methodsMap.get(key).keySet()) {
                String pth = className + "." + methodName.replace("()", "");
                methodsPth.put(pth, path);
            }
        }
        methodsMap.put(key, methodsPth);
    }

}
