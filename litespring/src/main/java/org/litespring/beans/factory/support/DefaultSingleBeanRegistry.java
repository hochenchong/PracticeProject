package org.litespring.beans.factory.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.litespring.beans.factory.config.SingleBeanRegistry;
import org.litespring.util.Assert;

public class DefaultSingleBeanRegistry implements SingleBeanRegistry {

	private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(64); 
	
	@Override
	public void registerSingleton(String beanName, Object singletonObject) {
		Assert.notNull(beanName, "'beanName' must not be null");
		
		// 判断该 Bean 是否已经存在了
		Object oldObject = this.singletonObjects.get(beanName);
		if (null != oldObject) {
			throw new IllegalStateException("Could not register object [" + singletonObject + 
					"] under bean name '" + beanName + "': there is alrealy object [" + oldObject + "] bound");
		}
		
		this.singletonObjects.put(beanName, singletonObject);
	}

	@Override
	public Object getSingleton(String beanName) {
		return this.singletonObjects.get(beanName);
	}

}
