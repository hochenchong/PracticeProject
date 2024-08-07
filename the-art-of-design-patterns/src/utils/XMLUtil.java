package utils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * @author hochenchong
 * @date 2024/7/5
 */
public class XMLUtil {

    public static String getString(String tagName) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("the-art-of-design-patterns/config.xml"));
            // 获取文本结点
            NodeList list = doc.getElementsByTagName(tagName);
            Node node = list.item(0).getFirstChild();
            return node.getNodeValue().trim();
        } catch (Exception e) {
            // 简单打印
            e.printStackTrace();
            return null;
        }
    }

    public static Object getBean(String tagName) {
        String tagValue = getString(tagName);
        if (tagValue == null) {
            return null;
        }
        try {
            Class<?> aClass = Class.forName(tagValue);
            return aClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            // 简单打印
            e.printStackTrace();
            return null;
        }
    }
}
