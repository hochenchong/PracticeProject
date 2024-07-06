package pattern15;

/**
 * 加密外观类
 *
 * @author hochenchong
 * @date 2024/07/06
 */
public class EncryptFacade {
    private final FileReader fileReader;
    private final CipherMachine cipherMachine;
    private final FileWriter fileWriter;

    public EncryptFacade() {
        fileReader = new FileReader();
        cipherMachine = new CipherMachine();
        fileWriter = new FileWriter();
    }

    public void fileEncrypt(String fileNameSrc, String fileNameDes) {
        String plainStr = fileReader.read(fileNameSrc);
        String encrypt = cipherMachine.encrypt(plainStr);
        fileWriter.write(encrypt, fileNameDes);
    }
}
