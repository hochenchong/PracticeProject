package hochenchong.hanyi;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class HanyiUtilsTest {

    /**
     * 获取汉仪字体信息相关的 json 数据
     */
    @Test
    public void testGetFontsInfoJsonStr() {
        String hanyiFontsInfoJsonStr = HanyiUtils.getFontsInfoJsonStr();
        // 简单的判断是否为 null
        Assert.assertNotNull(hanyiFontsInfoJsonStr);
    }

    /**
     * 获取汉仪字体信息相关的 List
     */
    @Test
    public void testGetFontsInfoBeans() {
        List<HanyiBean> hanyiBeans = HanyiUtils.getFontsInfoBeans();
        // 简单的判断是否为 null
        Assert.assertNotNull(hanyiBeans);
    }

    /**
     * 从汉仪官网上下载字体
     */
    @Test
    public void testDownloadFont() {
        List<HanyiBean> hanyiBeans = HanyiUtils.getFontsInfoBeans();
        String url = hanyiBeans.get(0).getProductFileUrl();
        String pathName = hanyiBeans.get(0).getProductName();
        String downloadStatus = HanyiUtils.downloadFont(url, pathName);
        // 简单的判断是否为 null
        System.out.println(downloadStatus);
        Assert.assertNotNull(downloadStatus);
    }
}