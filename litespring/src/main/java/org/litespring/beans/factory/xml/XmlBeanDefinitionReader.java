package org.litespring.beans.factory.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;
import org.litespring.beans.factory.support.GenericBeanDefinition;
import org.litespring.core.io.Resource;
import org.litespring.util.StringUtils;

/**
 * @Description: 负责读取 xml 和解析 xml
 * @author: HochenChong
 * @date: 2018-12-08
 * @version v0.1
 */
public class XmlBeanDefinitionReader  {
	public static final String ID_ATTRIBUTE = "id";
	
	public static final String CLASS_ATTRIBUTE = "class";
	
	public static final String SCOPE_ATTRIBUTE = "scope";
	
	public static final String PROPERTY_ELEMENT = "property";
	
	public static final String REF_ATTRIBUTE = "ref";
	
	public static final String VALUE_ATTRIBUTE = "value";
	
	public static final String NAME_ATTRIBUTE = "name";

	BeanDefinitionRegistry beanDefinitionRegistry;
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
		this.beanDefinitionRegistry = beanDefinitionRegistry;
	}

	/**
	 * 使用 dom4j 解析传进来的 xml 文件
	 * @param configFile
	 */
	public void loadBeanDefinitions(Resource resource) {
		InputStream is = null;
		try {
			is = resource.getInputStream();
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(is);
			
			Element root = document.getRootElement(); // <beans>
			Iterator<Element> iterator = root.elementIterator();
			
			while (iterator.hasNext()) {
				Element element = (Element) iterator.next();
				String id = element.attributeValue(ID_ATTRIBUTE);
				String beanClassName = element.attributeValue(CLASS_ATTRIBUTE);
				BeanDefinition beanDefinition = new GenericBeanDefinition(id, beanClassName);
				
				// 判断是否有 scope 属性
				String scope = element.attributeValue(SCOPE_ATTRIBUTE);
				if (scope != null) {
					beanDefinition.setScope(scope);
				}
				// 解析该 Bean 里面的 Property 元素
				parsePropertyElement(element, beanDefinition);
				// this.beanDefinitionMap.put(id, beanDefinition);
				this.beanDefinitionRegistry.registryBeanDefinition(id, beanDefinition);
			}
		} catch (Exception e) {
			throw new BeanDefinitionStoreException("IOException parsing XML document " + resource.getDescription() + " failed", e);
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void parsePropertyElement(Element beanElem, BeanDefinition bd) {
		Iterator iter = beanElem.elementIterator(PROPERTY_ELEMENT);
		while (iter.hasNext()) {
			Element propElem = (Element)iter.next();
			String propertyName = propElem.attributeValue(NAME_ATTRIBUTE);
			if (!StringUtils.hasLength(propertyName)) {
				logger.fatal("Tag 'property' must have a 'name' attribute");
				return;
			}
			
			Object val = parsePropertyValue(propElem, bd, propertyName);
			PropertyValue pv = new PropertyValue(propertyName, val);
			
			bd.getPropertyValues().add(pv);
		}
		
	}
	
	public Object parsePropertyValue(Element ele, BeanDefinition bd, String propertyName) {
		String elementName = (propertyName != null) ?
						"<property> element for property '" + propertyName + "'" :
						"<constructor-arg> element";

		
		boolean hasRefAttribute = (ele.attribute(REF_ATTRIBUTE) != null);
		boolean hasValueAttribute = (ele.attribute(VALUE_ATTRIBUTE) != null);
		
		if (hasRefAttribute) {
			String refName = ele.attributeValue(REF_ATTRIBUTE);
			if (!StringUtils.hasText(refName)) {
				logger.error(elementName + " contains empty 'ref' attribute");
			}
			RuntimeBeanReference ref = new RuntimeBeanReference(refName);			
			return ref;
		} else if (hasValueAttribute) {
			TypedStringValue valueHolder = new TypedStringValue(ele.attributeValue(VALUE_ATTRIBUTE));
			
			return valueHolder;
		} else {
			throw new RuntimeException(elementName + " must specify a ref or value");
		}
	}
}
