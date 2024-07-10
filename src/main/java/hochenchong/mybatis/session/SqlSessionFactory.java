package hochenchong.mybatis.session;

/**
 * @author hochenchong
 * @date 2024/07/10
 */
public interface SqlSessionFactory {
    SqlSession openSession();

    Configuration getConfiguration();
}
