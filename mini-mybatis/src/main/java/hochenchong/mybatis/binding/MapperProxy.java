package hochenchong.mybatis.binding;

import hochenchong.mybatis.session.SqlSession;
import hochenchong.mybatis.util.MapUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 实现 InvocationHandler
 *
 * @author hochenchong
 * @date 2024/07/10
 */
public class MapperProxy<T> implements InvocationHandler {
    private final SqlSession sqlSession;
    private final Class<T> mapperInterface;
    private final Map<Method, MapperMethodInvoker> methodCache;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface, Map<Method, MapperMethodInvoker> methodCache) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
        this.methodCache = methodCache;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if (Object.class.equals(method.getDeclaringClass())) {
                return method.invoke(this, args);
            }
            // 获取代理类调用代理方法
            return cachedInvoker(method).invoke(proxy, method, args, sqlSession);
        } catch (Throwable t) {
            // throw ExceptionUtil.unwrapThrowable(t);
            throw t;
        }
    }

    private MapperMethodInvoker cachedInvoker(Method method) throws Throwable {
        try {
            return MapUtil.computeIfAbsent(methodCache, method, m -> {
                // 简化
                return new PlainMethodInvoker(new MapperMethod(mapperInterface, method, sqlSession.getConfiguration()));
            });
        } catch (RuntimeException re) {
            Throwable cause = re.getCause();
            throw cause == null ? re : cause;
        }
    }

    interface MapperMethodInvoker {
        Object invoke(Object proxy, Method method, Object[] args, SqlSession sqlSession) throws Throwable;
    }

    private static class PlainMethodInvoker implements MapperMethodInvoker {
        private final MapperMethod mapperMethod;

        public PlainMethodInvoker(MapperMethod mapperMethod) {
            this.mapperMethod = mapperMethod;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args, SqlSession sqlSession) throws Throwable {
            // 调用 MapperMethod 里的 execute 执行
            return mapperMethod.execute(sqlSession, args);
        }
    }
}