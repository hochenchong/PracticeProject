package org.litespring.test.v1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.service.v1.PetStoreService;

public class BeanFactoryTest {

	/*
	 * 给定一个 xml 配置的文件（内含 bean 的定义），能够从中获取：
	 *    1. Bean 的定义
	 *    2. Bean 的实例
	 */
	@Test
	public void testGetBean() {
		BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
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
		BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
		
		try {
			factory.getBean("invalidBean");
		} catch (BeanCreationException e) {
			return ;
		}
		
		Assert.fail("BeanDefinitionStoreException 测试失败");
	}

}
