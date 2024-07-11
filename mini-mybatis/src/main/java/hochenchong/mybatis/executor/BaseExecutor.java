package hochenchong.mybatis.executor;

import hochenchong.mybatis.mapping.MappedStatement;
import hochenchong.mybatis.session.Configuration;
import hochenchong.test.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author hochenchong
 * @date 2024/07/11
 */
public class BaseExecutor implements Executor {
    protected Configuration configuration;

    public BaseExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> E query(MappedStatement ms, Object parameter) {
        DataSource dataSource = configuration.getEnvironment().getDataSource();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pre = connection.prepareStatement(ms.getSql())) {
            pre.setString(1, parameter.toString());
            ResultSet set = pre.executeQuery();
            // 这里就不反射创建对象和赋值类，简单化，主要学习过程
            // Object o = ms.getResultType().newInstance();
            User user = new User();
            if (set.next()) {
                user.setId(set.getString(1));
                user.setUsername(set.getString(2));
                user.setPassword(set.getString(3));
            }
            return (E) user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
