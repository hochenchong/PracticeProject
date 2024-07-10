package hochenchong.mybatis.session;

import hochenchong.mybatis.session.defaults.DefaultSqlSessionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * SqlSession工厂建造类
 *     通过不同的输入，转化为 Configuration 来构建 SqlSessionFactory
 *     原版看：org.apache.ibatis.session.SqlSessionFactoryBuilder
 *
 * @author hochenchong
 * @date 2024/07/10
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream inputStream) {
        return build(inputStream, null, null);
    }

    public SqlSessionFactory build(InputStream inputStream, String environment, Properties properties) {
        try {
            // 这里使用 dom4j 解析，将 xml 转换为 configuration 对象
//            XMLConfigBuilder parser = new XMLConfigBuilder(inputStream, environment, properties);
//            return build(parser.parse());
            return null;
        } catch (Exception e) {
            // throw ExceptionFactory.wrapException("Error building SqlSession.", e);
            throw e;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                // Intentionally ignore. Prefer previous error.
            }
        }
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }
}
