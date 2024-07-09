package structural.facade;

/**
 * 数据加密类
 *     简单处理，对 7 求余
 * @author hochenchong
 * @date 2024/07/06
 */
public class CipherMachine {
    public String encrypt(String plainText) {
        System.out.print("数据加密，将明文转换为密文：");
        StringBuilder es = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            String c = String.valueOf(plainText.charAt(i) % 7);
            es.append(c);
        }
        System.out.println(es);
        return es.toString();
    }
}
