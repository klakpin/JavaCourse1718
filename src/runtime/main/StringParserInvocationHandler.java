package runtime.main;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.jar.JarFile;

public class StringParserInvocationHandler implements InvocationHandler {

    private ThreadDispatcher.InputType inputType;
    private char currentType;
    private Object object;
    private MyClassLoader loader;

    public StringParserInvocationHandler(ThreadDispatcher.InputType inputType) {
        this.inputType = inputType;
        currentType = 'A';
    }

    public void setParserType(ThreadDispatcher.InputType parserType) {

        char letter;
        switch (parserType.getType()) {
            case 'A':
                letter = 'A';
                break;
            case 'B':
                letter = 'B';
                break;
            default:
                System.out.println("Unknown parser type: " + parserType.getType());
                return;
        }

        currentType = letter;

        try {
            object = loader
                    .loadClass("runtime.main.StringParser" + letter)
                    .newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.out.println("Cannot instantiate a class");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (currentType != inputType.getType()) {
            setParserType(inputType);
        }

        if (object == null) {
            if (loader == null) {
                loader = new MyClassLoader(new JarFile(new File("out/artifacts/Proxy_hw_jar/Proxy_hw.jar")));
            }
            object = loader
                    .loadClass("runtime.main.StringParser" + 'A')
                    .newInstance();
        }
        return method.invoke(object, args);
    }
}
