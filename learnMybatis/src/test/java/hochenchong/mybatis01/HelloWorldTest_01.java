package hochenchong.mybatis01;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import hochenchong.mybatis01.bean.Employee;
import hochenchong.mybatis01.dao.EmployeeMapper;

/**
 * 1. 接口式编程
 * 		 原生：            Dao ===》 DaoImpl
 * 		mybatis：   Mapper ==》 xxMapper.xml
 * 2. SqlSession 代表和数据库的一次会话，用完必须关掉
 * 3. SqlSession 和 connection 一样都是非线程安全的。每次使用都应该去获取新的对象
 * 4. mapper 接口没有实现类，但是 MyBatis 会为这个接口生成一个代理对象
 * 		将接口和 xml 进行绑定生成的代理对象
 * 5. 两个重要的配置文件：
 * 		MyBatis 的全局配置文件：数据库连接池信息，事务管理器信息等。。。系统运行环境信息
 * 		SQL 映射文件：保存了每一个 SQL 语句的映射信息；将 SQL 抽取出来
 * @date 2018-08-27
 */

public class HelloWorldTest_01 {

	/**
	 * 1. 根据 xml 配置文件（全局配置文件）创建一个 SqlSessionFactory
	 * 		 有数据源一些运行环境信息
	 * 2. SQL 映射文件：配置了每一个 SQL，以及 SQL 的封装规则等
	 * 3. 将 SQL 映射文件注册在全局配置文件中
	 * 4. 写代码：
	 * 		根据全局配置文件得到 SqlSessionFactory
	 * 		使用 SqlSessionFactory 获取到 SqlSession 对象来执行增删改查
	 * 		       一个 SqlSession 代表着和数据库的一次会话，用完要关闭
	 * 		使用 SQL 的唯一标识来告诉 MyBatis 执行哪个 SQL
	 * @return
	 * @throws IOException
	 */
	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}

	/*
	 * 以前版本的 MyBatis 的操作方式
	 */
	@Test
	public void testPerviousVersionsOfMyBatis() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		// 获取 SqlSession 对象，来直接执行已经映射的 SQL 语句
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Employee employee = (Employee) session.selectOne("hochenchong.mybatis01.EmployeeMapper.selectEmp", 1);
			System.out.println(employee);
		} finally {
			session.close();
		}
	}
	
	/*
	 * 使用接口式编程
	 *    优点：解耦，类型检查
	 */
	@Test
	public void testNowVersionOfMyBatis() throws IOException {
		// 创建一个 SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		
		// 获取 SqlSession 对象
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			// 获取接口的实现类对象，会为接口自动创建一个代理对象，代理对象去执行增删改查
			// 优点：检查输入输出
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			Employee employee = mapper.selectEmp(1);
			System.out.println(mapper);
			System.out.println(employee);
		} finally {
			session.close();
		}
	}
}
