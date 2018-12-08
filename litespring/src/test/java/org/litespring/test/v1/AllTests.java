package org.litespring.test.v1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @Description: 测试套件，将 org.litespring.test.v1 下的所有单元测试类组成一个套件
 * @author: HochenChong
 * @date: 2018-12-08
 * @version v0.1
 */

@RunWith(Suite.class)
@SuiteClasses({
	ApplicationContextTest.class, 
	BeanFactoryTest.class,
	Dom4jTest.class, 
	ResourceTest.class})
public class AllTests {

}
