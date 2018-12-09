package org.litespring.context.support;

import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

public class FileSystemXmlApplicationContext implements ApplicationContext {
	private DefaultBeanFactory factory = null;
	
	/**
	 * 从文件系统路径去读取 xml 文件，产生一个 ApplicationContext
	 * @param configFile
	 */
	public FileSystemXmlApplicationContext(String configFile) {
		factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(factory);
		Resource resource = new FileSystemResource(configFile);
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
