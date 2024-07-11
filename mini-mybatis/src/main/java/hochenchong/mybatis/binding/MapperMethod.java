package hochenchong.mybatis.binding;

import hochenchong.mybatis.mapping.MappedStatement;
import hochenchong.mybatis.mapping.SqlCommandType;
import hochenchong.mybatis.session.Configuration;
import hochenchong.mybatis.session.SqlSession;

import java.lang.reflect.Method;

/**
 * @author hochenchong
 * @date 2024/07/11
 */
public class MapperMethod {
    private final SqlCommand command;

    public MapperMethod(Class<?> mapperInterface, Method method, Configuration config) {
        this.command = new SqlCommand(config, mapperInterface, method);
    }

    public Object execute(SqlSession sqlSession, Object[] args) {
        Object result = null;
        switch (command.getType()) {
            // 这里只处理 select 查询一条的情况
            case SELECT:
                Object param = args[0];
                result = sqlSession.selectOne(command.getName(), param);
                break;
            default:
                throw new BindingException("Unknown execution method for: " + command.getName());
        }
        if (result == null) {
            throw new BindingException();
        }
        return result;
    }

    public static class SqlCommand {

        private final String name;
        private final SqlCommandType type;

        public SqlCommand(Configuration configuration, Class<?> mapperInterface, Method method) {
            final String methodName = method.getName();
            final Class<?> declaringClass = method.getDeclaringClass();
            MappedStatement ms = resolveMappedStatement(mapperInterface, methodName, declaringClass, configuration);
            if (ms == null) {
                name = null;
                type = SqlCommandType.FLUSH;
            } else {
                name = ms.getId();
                type = ms.getSqlCommandType();
                if (type == SqlCommandType.UNKNOWN) {
                    throw new BindingException("Unknown execution method for: " + name);
                }
            }
        }

        public String getName() {
            return name;
        }

        public SqlCommandType getType() {
            return type;
        }
    }

    private static MappedStatement resolveMappedStatement(Class<?> mapperInterface, String methodName, Class<?> declaringClass,
                                                          Configuration configuration) {
        // String statementId = mapperInterface.getName() + "." + methodName;
        // 这里简化，直接用方法名了
        String statementId = methodName;
        if (configuration.hasStatement(statementId)) {
            return configuration.getMappedStatement(statementId);
        }
        if (mapperInterface.equals(declaringClass)) {
            return null;
        }
        for (Class<?> superInterface : mapperInterface.getInterfaces()) {
            if (declaringClass.isAssignableFrom(superInterface)) {
                MappedStatement ms = resolveMappedStatement(superInterface, methodName, declaringClass, configuration);
                if (ms != null) {
                    return ms;
                }
            }
        }
        return null;
    }
}