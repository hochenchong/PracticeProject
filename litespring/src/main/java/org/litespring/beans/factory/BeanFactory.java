package org.litespring.beans.factory;

import org.litespring.beans.BeanDefinition;

public interface BeanFactory {

	/**
	 * 根据 beanId 获取 Bean 的定义
	 * @param beanId
	 * @return
	 */
	/* 2018-12-8 将此方法移动到 BeanDefinitionRegistry 接口下
	BeanDefinition getBeanDefinition(String beanId);
	*/

	/**
	 * 根据 beanId 获取 Bean
	 * @param beanId
	 * @return
	 */
	Object getBean(String beanId);

}
