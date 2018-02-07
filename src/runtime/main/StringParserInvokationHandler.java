package runtime.main;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.jar.JarFile;

public class StringParserInvokationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Invoked");
        Object object;
        MyClassLoader loader;
//        if (worker == null) {
//            if (myLoader == null) {
        loader = new MyClassLoader(new JarFile(new File("out/artifacts/Proxy_hw_jar/Proxy_hw.jar")));
//            }
         object = loader
                .loadClass("runtime.main.StringParserImpl")
                .newInstance();
//        }

        return method.invoke(object, args);
    }
}
