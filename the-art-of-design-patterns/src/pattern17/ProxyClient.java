package pattern17;

import utils.XMLTagNameConstant;
import utils.XMLUtil;

/**
 * 代理模式客户端
 *
 * @author hochenchong
 * @date 2024/07/06
 */
public class ProxyClient {
    public static void main(String[] args) {
        Searcher searcher = (Searcher) XMLUtil.getBean(XMLTagNameConstant.PROXY);
        assert searcher != null;
        searcher.doSearch("张三", "测试一下");
        System.out.println("---------");
        searcher.doSearch("李四", "也测试一下");
    }
}
