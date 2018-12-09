package org.litespring.test.v1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.service.v1.PetStoreService;

public class BeanFactoryTest {
	DefaultBeanFactory factory = null;
	XmlBeanDefinitionReader xmlBeanDefinitionReader = null;
	
	/*
	 * 抽取出测试方法中的重复代码
	 * @Before 注解的方法表示，在每个测试用例执行之前调用此方法 
	 */
	@Before
	public void setUp() {
		factory = new DefaultBeanFactory();
		xmlBeanDefinitionReader = new XmlBeanDefinitionReader(factory);
	}
	
	/*
	 * 给定一个 xml 配置的文件（内含 bean 的定义），能够从中获取：
	 *    1. Bean 的定义
	 *    2. Bean 的实例
	 */
	@Test
	public void testGetBean() {
		// BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
		xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("petstore-v1.xml"));
		
		BeanDefinition bd = factory.getBeanDefinition("petStore");
		
		assertEquals("org.litespring.service.v1.PetStoreService", bd.getBeanClassName());
		
		PetStoreService petStore = (PetStoreService)factory.getBean("petStore");
		
		assertNotNull(petStore);
	}
	
	/*
	 * 异常测试：获取无效的 Bean
	 */
	@Test
	public void testInvalidBean() {
		xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("petstore-v1.xml"));
		
		try {
			factory.getBean("invalidBean");
		} catch (BeanCreationException e) {
			return ;
		}
		
		Assert.fail("BeanDefinitionStoreException 测试失败");
	}

	/*
	 * 异常测试：无效的 XML 读取
	 */
	@Test
	public void testInvalidXML() {
		try {
			xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("xxx.xml"));
		} catch (BeanDefinitionStoreException e) {
			return ;
		}
		
		Assert.fail("BeanDefinitionStoreException 测试失败");
	}
}