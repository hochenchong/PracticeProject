package org.litespring.context.support;

import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.Resource;
import org.litespring.util.ClassUtils;

public abstract class AbstractApplicationContext implements ApplicationContext {
	private DefaultBeanFactory factory = null;
	private ClassLoader classLoader;

	/**
	 * 根据给定的路径读取 xml 文件，产生一个 ApplicationContext
	 * 
	 * @param configFile
	 */
	public AbstractApplicationContext(String configFile) {
		factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(factory);
		Resource resource = this.getResourceByPath(configFile);
		xmlBeanDefinitionReader.loadBeanDefinitions(resource);
		factory.setBeanClassLoader(this.classLoader);
	}

	/**
	 * 根据 BeanId 获取 Bean 对象
	 */
	@Override
	public Object getBean(String beanId) {
		return factory.getBean(beanId);
	}

	public abstract Resource getResourceByPath(String configFile);

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	@Override
	public ClassLoader getBeanClassLoader() {
		return (this.classLoader != null
				? this.classLoader
				: ClassUtils.getDefaultClassLoader());
	}
}
