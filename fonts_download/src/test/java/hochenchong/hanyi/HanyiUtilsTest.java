package hochenchong.hanyi;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class HanyiUtilsTest {

    /**
     * 获取汉仪字体信息相关的 json 数据
     */
    @Test
    public void getFontsInfoJsonStr() {
        String hanyiFontsInfoJsonStr = HanyiUtils.getFontsInfoJsonStr();
        // 简单的判断是否为 null
        Assert.assertNotNull(hanyiFontsInfoJsonStr);
    }

    /**
     * 获取汉仪字体信息相关的 List
     */
    @Test
    public void testJsonToBean() {
        List<HanyiBean> hanyiBeans = HanyiUtils.getFontsInfoBeans();
        // 简单的判断是否为 null
        Assert.assertNotNull(hanyiBeans);
    }
}