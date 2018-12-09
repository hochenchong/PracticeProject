package org.litespring.context.support;

import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;

public class ClassPathXmlApplicationContext implements ApplicationContext {
	private DefaultBeanFactory factory = null;
	
	/**
	 * 从 ClassPath 路径去读取 xml 文件，产生一个 ApplicationContext
	 * @param configFile
	 */
	public ClassPathXmlApplicationContext(String configFile) {
		factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(factory);
		Resource resource = new ClassPathResource(configFile);
		xmlBeanDefinitionReader.loadBeanDefinitions(resource);
	}

	/**
	 * 根据 BeanId 获取 Bean 对象
	 */
	@Override
	public Object getBean(String beanId) {
		return factory.getBean(beanId);
	}

}
