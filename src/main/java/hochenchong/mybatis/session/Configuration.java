package hochenchong.mybatis.session;

import hochenchong.mybatis.binding.MapperRegistry;
import hochenchong.mybatis.session.defaults.DefaultSqlSession;

/**
 * @author hochenchong
 * @date 2024/07/10
 */

public class Configuration {
    protected final MapperRegistry mapperRegistry = new MapperRegistry(this);

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }
}
