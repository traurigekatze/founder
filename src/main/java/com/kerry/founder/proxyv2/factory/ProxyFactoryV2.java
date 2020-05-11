package com.kerry.founder.proxyv2.factory;

import com.kerry.founder.proxyv2.handler.MyInvocationHandler;
import org.apache.commons.lang3.StringUtils;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author kerryhe
 * @date 2020/5/9
 */
public class ProxyFactoryV2 {

    private ProxyFactoryV2() { }

    public static Object newInstance(Class targetInf, MyInvocationHandler handler) {
        Object proxyBean = null;
        String tab = "\t";
        String line = "\n";
        StringBuilder packageLine = new StringBuilder("package com.kerry.proxyv2;").append(line);
        StringBuilder importLine = new StringBuilder("import com.kerry.founder.proxyv2.handler.MyInvocationHandler;").append(line)
                .append("import ").append(targetInf.getName()).append(";").append(line)
                .append("import java.lang.Exception;").append(line)
                .append("import java.lang.reflect.Method;").append(line);
        StringBuilder classLine = new StringBuilder("public class $Proxy implements ").append(targetInf.getSimpleName()).append(" {").append(line);
        StringBuilder fieldLine = new StringBuilder(tab).append("private MyInvocationHandler handler;").append(line);

        StringBuilder constructorLine = new StringBuilder(tab).append("public $Proxy(MyInvocationHandler handler) {").append(line)
                .append(tab).append(tab).append("this.handler = handler;").append(line).append(tab).append("}").append(line);


        StringBuilder methodLine = new StringBuilder();
        Method[] methods = targetInf.getMethods();
        if (methods != null && methods.length > 0) {
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                String methodName = method.getName();
                String rtType = method.getReturnType().getSimpleName();
                StringBuilder parameterLine = new StringBuilder();
                Class[] args = method.getParameterTypes();
                StringBuilder argsLine = new StringBuilder(", new Object[]{");
                StringBuilder params = new StringBuilder();
                if (args != null) {
                    for (int j = 0; j < args.length; j++) {
                        Class arg = args[j];
                        parameterLine.append(arg.getSimpleName()).append(" p").append(j).append(", ");
                        argsLine.append("p").append(j).append(", ");
                        params.append(", ").append(arg.getSimpleName()).append(".class");
                    }
                }
                if (StringUtils.isNotBlank(parameterLine)) {
                    parameterLine = parameterLine.replace(parameterLine.lastIndexOf(","), parameterLine.length()+1, "");
                    argsLine = argsLine.replace(argsLine.lastIndexOf(","), argsLine.length() + 1, "");
                }
                argsLine.append("}");
                methodLine.append(tab).append("public ").append(rtType).append(" ").append(methodName).append("(").append(parameterLine).append(") throws Exception {").append(line)
                        .append(tab).append(tab).append("Method method = Class.forName(\"").append(targetInf.getName()).append("\").getDeclaredMethod(\"").append(methodName)
                        .append("\"").append(params).append(");").append(line)
                        .append(tab).append(tab);
                if (!"void".equals(rtType)) {
                    methodLine.append("return (").append(rtType).append(")");
                }
                methodLine.append("handler.invoke(method").append(argsLine).append(");").append(line)
                        .append(tab).append("}").append(line);
            }
        }
        StringBuilder content = new StringBuilder();
        content.append(packageLine).append(importLine).append(classLine).append(fieldLine).append(constructorLine).append(methodLine).append("}");
        File file =new File("/Users/kerryhe/mine/private/com/kerry/proxyv2/$Proxy.java");
//        File file =new File("E:\\com\\kerry\\proxy\\$Proxy.java");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            fw.write(content.toString());
            fw.flush();
            fw.close();

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
            Iterable units = fileMgr.getJavaFileObjects(file);

            JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
            t.call();
            fileMgr.close();

            URL[] urls = new URL[]{new URL("file:/Users/kerryhe/mine/private/")};
//            URL[] urls = new URL[]{new URL("file:E:\\\\")};
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            Class clazz = urlClassLoader.loadClass("com.kerry.proxyv2.$Proxy");

            Constructor constructor = clazz.getConstructor(MyInvocationHandler.class);
            proxyBean = constructor.newInstance(handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proxyBean;
    }

}
