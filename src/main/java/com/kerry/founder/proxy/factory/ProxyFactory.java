package com.kerry.founder.proxy.factory;

import com.kerry.founder.proxy.service.UserService;
import com.kerry.founder.proxy.service.UserServiceImpl;
import com.kerry.founder.proxy.service.WillBeServiceImpl;
import org.apache.commons.lang3.StringUtils;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author kerryhe
 * @date 2020/5/9
 */
public class ProxyFactory {

    public static Object newInstance(Object target) {
        Object proxyBean = null;
        String tab = "\t";
        String line = "\n";
        Class targetInf = target.getClass().getInterfaces()[0];
        StringBuilder packageLine = new StringBuilder("package com.kerry.proxy;").append(line);
        StringBuilder importLine = new StringBuilder("import ").append(targetInf.getName()).append(";").append(line);
        StringBuilder classLine = new StringBuilder("public class $Proxy implements ").append(targetInf.getSimpleName()).append(" {").append(line);
        StringBuilder fieldLine = new StringBuilder(tab).append("private ").append(targetInf.getSimpleName()).append(" target;").append(line);

        StringBuilder constructorLine = new StringBuilder(tab).append("public $Proxy(").append(targetInf.getSimpleName()).append(" target) {").append(line)
                .append(tab).append(tab).append("this.target = target;").append(line).append(tab).append("}").append(line);


        StringBuilder methodLine = new StringBuilder();
        Method[] methods = targetInf.getMethods();
        if (methods != null && methods.length > 0) {
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                String methodName = method.getName();
                String rtType = method.getReturnType().getName();
                StringBuilder parameterLine = new StringBuilder();
                Class[] args = method.getParameterTypes();
                StringBuilder argsLine = new StringBuilder();
                if (args != null) {
                    for (int j = 0; j < args.length; j++) {
                        Class arg = args[j];
                        parameterLine.append(arg.getSimpleName()).append(" p").append(j).append(", ");
                        argsLine.append("p").append(j).append(", ");
                    }
                }
                if (StringUtils.isNotBlank(parameterLine)) {
                    parameterLine = parameterLine.replace(parameterLine.lastIndexOf(","), parameterLine.length()+1, "");
                    argsLine = argsLine.replace(argsLine.lastIndexOf(","), argsLine.length() + 1, "");
                }
                String rt = "";
                if (!"void".equals(rtType)) {
                    rt = "return ";
                }
                methodLine.append(tab).append("public ").append(rtType).append(" ").append(methodName).append("(").append(parameterLine).append(") {").append(line)
                        .append(tab).append(tab).append("System.out.println(\"this is proxy,nice to meet you...\");").append(line)
                        .append(tab).append(tab).append(rt).append("target.").append(methodName).append("(").append(argsLine).append(");").append(line)
                        .append(tab).append("}").append(line);
            }
        }
        StringBuilder content = new StringBuilder();
        content.append(packageLine).append(importLine).append(classLine).append(fieldLine).append(constructorLine).append(methodLine).append("}");

        File file =new File("/Users/kerryhe/mine/private/com/kerry/proxy/$Proxy.java");
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
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            Class clazz = urlClassLoader.loadClass("com.kerry.proxy.$Proxy");

            Constructor constructor = clazz.getConstructor(targetInf);

            proxyBean = constructor.newInstance(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proxyBean;
    }

}
