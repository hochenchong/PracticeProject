package org.litespring.beans.factory.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.util.ClassUtils;

public class DefaultBeanFactory implements BeanFactory {
	
	public static final String ID_ATTRIBUTE = "id";
	
	public static final String CLASS_ATTRIBUTE = "class";
	
	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
	
	
	public DefaultBeanFactory(String configFile) {
		loadBeanDefinition(configFile);
	}

	/**
	 * 使用 dom4j 解析传进来的 xml 文件
	 * @param configFile
	 */
	private void loadBeanDefinition(String configFile) {
		InputStream is = null;
		
		try {
			ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
			is = classLoader.getResourceAsStream(configFile);
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(is);
			
			Element root = document.getRootElement(); // <beans>
			Iterator<Element> iterator = root.elementIterator();
			
			while (iterator.hasNext()) {
				Element element = (Element) iterator.next();
				String id = element.attributeValue(ID_ATTRIBUTE);
				String beanClassName = element.attributeValue(CLASS_ATTRIBUTE);
				BeanDefinition beanDefinition = new GenericBeanDefinition(id, beanClassName);
				this.beanDefinitionMap.put(id, beanDefinition);
			}
		} catch (DocumentException e) {
			throw new BeanDefinitionStoreException("IOException parsing XML document " + configFile + " failed", e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
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
		// 通过反射创建对象
		ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
		String beanClassName = beanDefinition.getBeanClassName();
		
		try {
			Class<?> clz = classLoader.loadClass(beanClassName);
			return clz.newInstance();
		} catch (Exception e) {
			throw new BeanCreationException("Create bean for " + beanClassName + " failed",e);
		}
	}
}
