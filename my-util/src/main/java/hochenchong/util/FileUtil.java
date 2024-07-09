package hochenchong.util;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HochenChong on 2020/04/21
 */

public class FileUtil {
    private final static String defaultCharsetName = "UTF-8";

    /**
     * 根据文件路径，以默认编码（UTF-8）读取文件内容
     *
     * @param filePath 文件路径
     * @return
     */
    public static String getFileContent(String filePath) {
        return getFileContent(filePath, defaultCharsetName);
    }

    /**
     * 根据文件路径，以指定的编码读取文件内容
     *
     * @param filePath 文件路径
     * @param charsetName 编码名
     * @return
     */
    public static String getFileContent(String filePath, String charsetName) {
        StringBuilder stringBuffer = new StringBuilder();

        try (InputStreamReader reader = new InputStreamReader(Files.newInputStream(new File(filePath).toPath()), charsetName)) {
            BufferedReader br = new BufferedReader(reader);
            String str;
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }

    /**
     * 以默认编码（UTF-8），将内容写入文件
     * @param filePath 内容要写入的文件路径
     * @param content 文件内容
     */
    public static void contentToFile(String filePath, String content) {
        contentToFile(filePath, content, defaultCharsetName);
    }

    /**
     * 以指定编码，将内容写入文件
     *
     * @param filePath 内容要写入的文件路径
     * @param content 文件内容
     * @param charsetName 编码
     */
    public static void contentToFile(String filePath, String content, String charsetName) {
        File file = new File(filePath);
        // 如果文件路径的父目录不存在，则创建其父目录
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Files.newOutputStream(file.toPath()), charsetName)) {
            outputStreamWriter.write(content);
            outputStreamWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据传入的根路径，获取该路径下的所有文件绝对路径
     *
     * @param rootFilePath
     * @return 包含所有绝对路径的 List
     */
    public static List<String> getAllFilePath(String rootFilePath) {
        List<String> filePathList = new ArrayList<>();

        getFilePaths(rootFilePath, filePathList);

        return filePathList;
    }

    private static void getFilePaths(String rootFilePath, List<String> filePathList) {
        File rootFile = new File(rootFilePath);
        // 如果根文件不存在，则直接返回
        if (!rootFile.exists()) {
            return;
        }
        // 如果根路径是文件，则在 List 添加后返回
        if (rootFile.isFile()) {
            filePathList.add(rootFile.getAbsolutePath());
            return;
        }

        File[] subFiles = rootFile.listFiles();
        for (File file : subFiles) {
            if (file.isFile()) {
                filePathList.add(file.getAbsolutePath());
            } else {
                // 如果是文件夹，则递归获取
                getFilePaths(file.getAbsolutePath(), filePathList);
            }
        }
    }
}
