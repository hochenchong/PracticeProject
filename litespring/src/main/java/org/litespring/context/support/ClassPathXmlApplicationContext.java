package org.litespring.context.support;

import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

	public ClassPathXmlApplicationContext(String configFile) {
		super(configFile);
	}

	/**
	 * 从 ClassPath 路径中获取 Resource
	 */
	@Override
	public Resource getResourceByPath(String configFile) {
		return new ClassPathResource(configFile);
	}

}
