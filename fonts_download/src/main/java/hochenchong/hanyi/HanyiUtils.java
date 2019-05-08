package hochenchong.hanyi;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * HanyiUtils
 *
 * @author HochenChong
 * @date 2019/5/8
 */

public class HanyiUtils {
    /**
     * @return 汉仪相关字体 json 字符串数据
     */
    public static String getHanyiFontsInfoJsonStr(){
        // 创建 HTTPClient 对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String resultJson = "";
        String url = "http://www.hanyi.studio/hanyiwebsite/fontmanagement/fontmanagerhandler.ashx?tags=";
        CloseableHttpResponse response = null;

        try {
            URIBuilder builder = new URIBuilder(url);
            URI uri = builder.build();

            // 创建 http Get 请求
            HttpGet httpGet = new HttpGet(uri);

            httpGet.addHeader("Origin", "http://www.hanyi.com.cn");
            httpGet.addHeader("Referer", "http://www.hanyi.com.cn/productList.php");
            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.75 Safari/537.36");
            // 执行返回请求
            response = httpClient.execute(httpGet);

            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultJson = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(resultJson);
                return resultJson;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
            // URIBuilder
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            // 执行请求
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 错误则返回 null
        return null;
    }
}
