package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

public interface BeanDefinitionRegistry {
	/**
	 * 根据 beanId 获取 Bean 的定义
	 * @param beanId
	 * @return
	 */
	BeanDefinition getBeanDefinition(String beanId);
	
	/**
	 * 注册 Bean 的定义
	 * @param beanId
	 * @param beanDefinition
	 */
	void registryBeanDefinition(String beanId, BeanDefinition beanDefinition);
}
