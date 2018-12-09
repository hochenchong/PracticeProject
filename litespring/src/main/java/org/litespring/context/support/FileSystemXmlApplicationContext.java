package org.litespring.context.support;

import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

public class FileSystemXmlApplicationContext extends AbstractApplicationContext {

	public FileSystemXmlApplicationContext(String configFile) {
		super(configFile);
	}

	/**
	 * 从文件系统路径中获取 Resource 对象
	 */
	@Override
	public Resource getResourceByPath(String configFile) {
		return new FileSystemResource(configFile);
	}
}
