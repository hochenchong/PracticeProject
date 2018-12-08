package org.litespring.beans.factory.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;
import org.litespring.beans.factory.support.GenericBeanDefinition;
import org.litespring.util.ClassUtils;

/**
 * @Description: 负责读取 xml 和解析 xml
 * @author: HochenChong
 * @date: 2018-12-08
 * @version v0.1
 */
public class XmlBeanDefinitionReader  {
	public static final String ID_ATTRIBUTE = "id";
	
	public static final String CLASS_ATTRIBUTE = "class";

	BeanDefinitionRegistry beanDefinitionRegistry;
	
	public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
		this.beanDefinitionRegistry = beanDefinitionRegistry;
	}

	/**
	 * 使用 dom4j 解析传进来的 xml 文件
	 * @param configFile
	 */
	public void loadBeanDefinitions(String configFile) {
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
				// this.beanDefinitionMap.put(id, beanDefinition);
				this.beanDefinitionRegistry.registryBeanDefinition(id, beanDefinition);
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
}
