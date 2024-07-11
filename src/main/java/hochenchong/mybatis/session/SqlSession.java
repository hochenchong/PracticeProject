package hochenchong.mybatis.session;

/**
 * @author hochenchong
 * @date 2024/07/10
 */
public interface SqlSession {
    <T> T getMapper(Class<T> type);

    Configuration getConfiguration();

    <T> T selectOne(String statement, Object parameter);
}
