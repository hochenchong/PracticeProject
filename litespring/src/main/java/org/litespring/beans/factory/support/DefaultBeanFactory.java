package org.litespring.beans.factory.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.litespring.util.ClassUtils;

public class DefaultBeanFactory extends DefaultSingleBeanRegistry
	implements ConfigurableBeanFactory, BeanDefinitionRegistry {
	
	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
	private ClassLoader classLoader;
	
	public DefaultBeanFactory() {
	}

	@Override
	public BeanDefinition getBeanDefinition(String beanId) {
		return this.beanDefinitionMap.get(beanId);
	}

	@Override
	public Object getBean(String beanId) {
		BeanDefinition beanDefinition = this.getBeanDefinition(beanId);
		
		if (beanDefinition == null) {
			throw new BeanCreationException("Bean Definition does not exist");
		}
		
		// 判断是否为singleton
		if (beanDefinition.isSingleton()) {
			Object bean = this.getSingleton(beanId);
			if (null == bean) {
				bean = createBean(beanDefinition);
				this.registerSingleton(beanId, bean);
			}
			return bean;
		}
		return createBean(beanDefinition);
	}
	private Object createBean(BeanDefinition beanDefinition) {
		// 通过反射创建对象
		ClassLoader classLoader = this.getBeanClassLoader();
		String beanClassName = beanDefinition.getBeanClassName();
		
		try {
			Class<?> clz = classLoader.loadClass(beanClassName);
			return clz.newInstance();
		} catch (Exception e) {
			throw new BeanCreationException("Create bean for " + beanClassName + " failed",e);
		}
	}

	/**
	 * 注册 Bean 的定义
	 */
	@Override
	public void registryBeanDefinition(String beanId,
			BeanDefinition beanDefinition) {
		this.beanDefinitionMap.put(beanId, beanDefinition);
	}

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
