package org.litespring.beans.factory;

import org.litespring.beans.BeanDefinition;

public interface BeanFactory {

	/**
	 * 根据 beanId 获取 Bean 的定义
	 * @param beanId
	 * @return
	 */
	BeanDefinition getBeanDefinition(String beanId);

	/**
	 * 根据 beanId 获取 Bean
	 * @param beanId
	 * @return
	 */
	Object getBean(String beanId);

}
