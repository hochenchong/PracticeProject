package hochenchong.mybatis.session.defaults;

import hochenchong.mybatis.session.Configuration;
import hochenchong.mybatis.session.SqlSession;
import hochenchong.mybatis.session.SqlSessionFactory;

/**
 * 默认的 SqlSessionFactory，用于获取默认的 SqlSession
 *
 * @author hochenchong
 * @date 2024/07/10
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration, null, false);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
