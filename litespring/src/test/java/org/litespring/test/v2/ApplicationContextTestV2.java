package org.litespring.test.v2;

import static org.junit.Assert.*;

import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v2.PetStoreService;

public class ApplicationContextTestV2 {

	@Test
	public void testGetBeanProperty() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("petstore-v2.xml");
		PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");
		
		assertNotNull(petStoreService.getAccountDao());
		assertNotNull(petStoreService.getItemDao());
	}

}
