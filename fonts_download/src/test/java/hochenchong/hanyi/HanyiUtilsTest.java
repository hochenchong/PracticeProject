package hochenchong.hanyi;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class HanyiUtilsTest {

    @Test
    public void getHanyiFontsInfoJsonStr() {
        String hanyiFontsInfoJsonStr = HanyiUtils.getHanyiFontsInfoJsonStr();
        Assert.assertNotNull(hanyiFontsInfoJsonStr);
    }
}