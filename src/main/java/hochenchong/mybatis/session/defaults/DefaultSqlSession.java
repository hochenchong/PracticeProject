package hochenchong.mybatis.session.defaults;

import hochenchong.mybatis.executor.Executor;
import hochenchong.mybatis.session.Configuration;
import hochenchong.mybatis.session.SqlSession;

/**
 * @author hochenchong
 * @date 2024/07/10
 */
public class DefaultSqlSession implements SqlSession {
    private final Configuration configuration;
    private final Executor executor;
    private final boolean autoCommit;

    public DefaultSqlSession(Configuration configuration, Executor executor, boolean autoCommit) {
        this.configuration = configuration;
        this.executor = executor;
        this.autoCommit = autoCommit;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }
}
