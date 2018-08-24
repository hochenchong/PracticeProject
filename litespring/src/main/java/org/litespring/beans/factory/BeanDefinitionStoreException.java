package org.litespring.beans.factory;

import org.litespring.beans.BeansException;

/**
 * @Description 读取 XML 文件出错时抛出异常
 */

public class BeanDefinitionStoreException extends BeansException {
	public BeanDefinitionStoreException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
