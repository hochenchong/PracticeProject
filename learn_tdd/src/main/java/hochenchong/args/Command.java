package hochenchong.args;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author HochenChong
 * @date 2019/09/02
 */
public class Command {

    private static final Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
    Map<String, String> commandMap = new HashMap<>();

    public Command(String commandStr) {
        String[] commandList = commandStr.split(" ");
        for (int i = 0; i < commandList.length; i++) {
            String key = commandList[i];
            if (key.startsWith("-")) {
                // 如果下一个也是 - 开头的，则判断是否是 key
                if (i + 1 < commandList.length) {
                    String value = commandList[i + 1];
                    if (!value.startsWith("-") || isNumber(value)) {
                        commandMap.put(key.substring(1), value);
                        i++;
                    } else {
                        commandMap.put(key.substring(1), null);
                    }
                } else {
                    commandMap.put(key.substring(1), null);
                }
            }
        }
    }

    public static boolean isNumber(String string) {
        if (string == null) {
            return Boolean.FALSE;
        }
        return pattern.matcher(string).matches();
    }

    public String getValue(String key) {
        System.out.println(commandMap);
        return commandMap.get(key);
    }
}
