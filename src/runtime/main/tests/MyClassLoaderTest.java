package runtime.main.tests;

import runtime.main.CharChecker;
import runtime.main.MyClassLoader;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static org.junit.Assert.*;

public class MyClassLoaderTest {

    @org.junit.Test
    public void loadClass() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        JarFile file = new JarFile(new File("out/artifacts/Proxy_hw_jar/Proxy_hw.jar"));
        MyClassLoader loader = new MyClassLoader(file);
        Enumeration<JarEntry> e = file.entries();

        CharChecker checker = (CharChecker) loader.loadClass("runtime.main.CharChecker").newInstance();
        assertEquals(true, checker.isCharValid('в'));
    }
}