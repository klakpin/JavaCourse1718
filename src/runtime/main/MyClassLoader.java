package runtime.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class MyClassLoader extends ClassLoader {

    JarFile jarFile;

    public MyClassLoader(JarFile jarFile) {
        super();
        this.jarFile = jarFile;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            JarEntry jarEntry = jarFile.getJarEntry(
                    name.replace(".", "/") + ".class");
            InputStream libInputStream = jarFile.getInputStream(jarEntry);

            byte[] classBytes = new byte[(int) jarEntry.getSize()];
            if (libInputStream.read(classBytes) != classBytes.length) {
                throw new IOException("No....");
            }

            return defineClass(name, classBytes, 0, classBytes.length);

        } catch (IOException e) {
            throw new ClassNotFoundException(e.getMessage(), e);
        }
    }
}
