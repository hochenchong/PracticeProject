package hochenchong.mybatis.session;

import hochenchong.mybatis.binding.MapperRegistry;
import hochenchong.mybatis.mapping.Environment;
import hochenchong.mybatis.mapping.MappedStatement;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author hochenchong
 * @date 2024/07/10
 */

public class Configuration {
    protected final MapperRegistry mapperRegistry = new MapperRegistry(this);
    // 用于存放映射内容
    private Map<String, MappedStatement> mappedStatements = new ConcurrentHashMap<>();

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

    public boolean hasStatement(String statementName) {
        return mappedStatements.containsKey(statementName);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }
}
