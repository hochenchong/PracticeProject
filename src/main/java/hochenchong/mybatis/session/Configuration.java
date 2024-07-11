package hochenchong.mybatis.session;

import hochenchong.mybatis.binding.MapperRegistry;
import hochenchong.mybatis.mapping.Environment;


/**
 * @author hochenchong
 * @date 2024/07/10
 */

public class Configuration {
    protected final MapperRegistry mapperRegistry = new MapperRegistry(this);

    protected Environment environment;

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
