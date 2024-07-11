package hochenchong.mybatis.xml;

import hochenchong.mybatis.io.Resources;
import hochenchong.mybatis.session.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * 用于解析 mapper.xml 文件
 *
 * @author hochenchong
 * @date 2024/07/11
 */
public class XMLMapperBuilder {
    private final InputStream inputStream;
    private final Configuration configuration;
    private final String resource;

    public XMLMapperBuilder(InputStream inputStream, Configuration configuration, String resource) {
        this.inputStream = inputStream;
        this.configuration = configuration;
        this.resource = resource;
    }

    public void parse() {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            Element root = document.getRootElement();
            // 获取命名空间，添加到配置中
            bindMapperForNamespace(root);
            for (Element child : root.elements()) {
                // 遍历里面的方法
                System.out.println(child.getName());
                System.out.println(child.getText().trim());
                System.out.println(child.attributeValue("id"));
                System.out.println(child.attributeValue("resultType"));
            }
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    private void bindMapperForNamespace(Element root) {
        String namespace = root.attributeValue("namespace");
        if (namespace != null) {
            Class<?> boundType = null;
            try {
                boundType = Resources.classForName(namespace);
            } catch (ClassNotFoundException e) {
                // ignore, bound type is not required
            }
            if (boundType != null && !configuration.hasMapper(boundType)) {
                // Spring may not know the real resource name so we set a flag
                // to prevent loading again this resource from the mapper interface
                // look at MapperAnnotationBuilder#loadXmlResource
                // configuration.addLoadedResource("namespace:" + namespace);
                configuration.addMapper(boundType);
            }
        }
    }
}
