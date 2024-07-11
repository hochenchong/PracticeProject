package hochenchong.mybatis.executor;

import hochenchong.mybatis.mapping.MappedStatement;

/**
 * @author hochenchong
 * @date 2024/07/10
 */
public interface Executor {

    <E> E query(MappedStatement ms, Object parameter);
}
