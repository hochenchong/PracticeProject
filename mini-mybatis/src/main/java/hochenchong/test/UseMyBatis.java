package hochenchong.test;

import hochenchong.mybatis.io.Resources;
import hochenchong.mybatis.session.SqlSession;
import hochenchong.mybatis.session.SqlSessionFactory;
import hochenchong.mybatis.session.SqlSessionFactoryBuilder;
import hochenchong.test.mapper.UserMapper;
import hochenchong.test.model.User;

import java.io.IOException;
import java.io.InputStream;

/**
 * 使用 MyBatis
 *
 * @author hochenchong
 * @date 2024/07/10
 */
public class UseMyBatis {
    public static void main(String[] args) throws IOException {
        // 官方 3.5.16 入门使用例子：https://mybatis.org/mybatis-3/zh_CN/getting-started.html
        // 从 XML 中构建 SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById("1");
        System.out.println(user);
    }
}
