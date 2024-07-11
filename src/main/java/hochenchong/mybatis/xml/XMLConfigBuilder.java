package hochenchong.mybatis.xml;

import hochenchong.mybatis.datasource.UnpooledDataSource;
import hochenchong.mybatis.io.Resources;
import hochenchong.mybatis.mapping.Environment;
import hochenchong.mybatis.session.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

/**
 * xml 解析工具，用于解析 config xml，解析 xml 返回 Configuration
 *
 * @author hochenchong
 * @date 2024/07/10
 */
public class XMLConfigBuilder {
    // 确保只能被解析一次
    private boolean parsed;
    private InputStream inputStream;
    private String environment;
    private Properties properties;

    protected final Configuration configuration;

    public XMLConfigBuilder(InputStream inputStream, String environment, Properties properties) {
        this.inputStream = inputStream;
        this.environment = environment;
        this.properties = properties;
        // 初始化一下
        this.configuration = new Configuration();
    }

    public Configuration parse() {
        // 这里使用 dom4j 解析，将 xml 转换为 configuration 对象
        if (parsed) {
            throw new RuntimeException("Each XMLConfigBuilder can only be used once.");
        }

        try {
            parsed = true;

            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            Element root = document.getRootElement();
            parseConfiguration(root);
            return configuration;
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    private void parseConfiguration(Element root) {
        try {
            // 这里省略了很多方法
            // 从 configuration 中获取数据库链接信息
            environmentsElement(root.element("environments"));
            mappersElement(root.element("mappers"));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }
    }

    private void environmentsElement(Element environments) {
        if (environments == null) {
            return;
        }
        String driver = null, url = null, username = null, password = null;
        // 获取 <dataSource> 元素及其属性和子元素
        Element dataSourceEle = environments.element("environment").element("dataSource");
        String id = environments.element("environment").attributeValue("id");
        if (dataSourceEle != null) {
            // 获取 <property> 元素及其属性和值
            for (Element property : dataSourceEle.elements("property")) {
                String name = property.attributeValue("name");
                String value = property.attributeValue("value");

                //赋值
                switch (name) {
                    case "driver" :
                        driver = value;
                        break;
                    case "url" :
                        url = value;
                        break;
                    case "username" :
                        username = value;
                        break;
                    case "password" :
                        password = value;
                        break;
                    default :
                        throw new RuntimeException("[database]: <property> unknown name");
                }
            }
            // 这里简化了判断是否为空，也简化了从工厂中获取，直接 new 出来
            DataSource dataSource = new UnpooledDataSource(driver, url, username, password);
            this.configuration.setEnvironment(new Environment(id, dataSource));
        }
    }

    private void mappersElement(Element context) throws Exception {
        if (context == null) {
            return;
        }
        for (Element child : context.elements()) {
            // 这里默认只处理 mapper 开头的的情况
            if ("package".equals(child.getName())) {
//                String mapperPackage = child.getStringAttribute("name");
//                configuration.addMappers(mapperPackage);
                continue;
            } else {
                // mapper 处理
                // 这里也简化，只处理 resource
                String resource = child.attributeValue("resource");
                if (resource != null) {
                    // 读取 mapper 里配置的 xml 文件
                    try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
                        XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, resource);
                        mapperParser.parse();
                    }
                } else {
                    throw new RuntimeException("A mapper element may only specify a url, resource or class, but not more than one.");
                }
            }
        }
    }
}
