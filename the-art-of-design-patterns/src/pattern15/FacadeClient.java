package pattern15;

/**
 * 客户端使用外观类
 *
 * @author hochenchong
 * @date 2024/07/06
 */
public class FacadeClient {
    public static void main(String[] args) {
        EncryptFacade encryptFacade = new EncryptFacade();
        encryptFacade.fileEncrypt("facade/src.txt", "facade/des.txt");
    }
}
