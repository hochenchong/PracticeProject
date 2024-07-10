package hochenchong.mybatis.io;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * org.apache.ibatis.io.Resources
 *
 * @author hochenchong
 * @date 2024/07/10
 */
public class Resources {
    private static final ClassLoader defaultClassLoader = ClassLoader.getSystemClassLoader();

    public static InputStream getResourceAsStream(String resource) throws IOException {
        return getResourceAsStream(null, resource);
    }

    public static InputStream getResourceAsStream(ClassLoader loader, String resource) throws IOException {
        InputStream in;
        if (loader == null) {
            in = defaultClassLoader.getResourceAsStream(resource);
        } else {
            in = loader.getResourceAsStream(resource);
        }
        if (in == null) {
            throw new IOException("Could not find resource " + resource);
        }
        return in;
    }
}
