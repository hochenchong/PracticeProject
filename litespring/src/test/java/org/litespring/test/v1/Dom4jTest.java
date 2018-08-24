package org.litespring.test.v1;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class Dom4jTest {
	@Test
	public void testGetTestResourcesFilePath() {
		String filePath = this.getClass().getResource("/petstore-v1.xml").getFile().toString();
		System.out.println(filePath);
	}
	
	@Test
	public void testDom4jGetElement() throws DocumentException {
		String filePath = this.getClass().getResource("/petstore-v1.xml").getFile().toString();
		
		// 创建 SAXReader 对象
		SAXReader reader = new SAXReader();
		// 读取文件 转换成 Document
		Document document = reader.read(new File(filePath));
		// 获取根节点元素对象
		Element root = document.getRootElement();
		List<Element> list = root.elements();

		for (Element element : list) {
			String id = element.attributeValue("id");
			System.out.println(id);
		}
	}
}
