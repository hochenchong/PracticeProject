package hochenchong.hanyi;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.*;
import java.util.List;

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
    public static String getFontsInfoJsonStr(){
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
                // System.out.println(resultJson);
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

    /**
     * 使用 FastJson 将 json 字符串转为 List
     * @return 汉仪相关字体信息的 List
     */
    public static List<HanyiBean> getFontsInfoBeans() {
        return JSON.parseArray(getFontsInfoJsonStr(), HanyiBean.class);
    }

    /**
     *
     * @param fontDownloadUrl 汉仪字体下载链接
     * @param pathName 字体保存的路径，包括文件名与后缀
     */
    public static String downloadFont(String fontDownloadUrl, String pathName) {
        URL url = null;
        try {
            url = new URL(fontDownloadUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(10 * 1000);
            // 防止屏蔽程序抓取而返回403错误
            // conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.108 Safari/537.36");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        try (InputStream is = conn.getInputStream();
             OutputStream os = new FileOutputStream(new File(pathName + ".ttf"));) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return pathName + " 》》》已下载完成";
    }
}
