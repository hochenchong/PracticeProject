package org.litespring.test.v1;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.context.support.FileSystemXmlApplicationContext;
import org.litespring.service.v1.PetStoreService;

public class ApplicationContextTest {

	@Test
	public void testGetBean() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("petstore-v1.xml");
		PetStoreService petStore = (PetStoreService)applicationContext.getBean("petStore");
		assertNotNull(petStore);
	}
	
	@Test
	public void testGetBeanFromFileSystemContext() {
		// 获取 petstore-v1.xml 在本机的绝对路径
		String filePath = this.getClass().getResource("/petstore-v1.xml").getFile().toString();
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext(filePath);
		PetStoreService petStore = (PetStoreService)applicationContext.getBean("petStore");
		assertNotNull(petStore);
	}
}
