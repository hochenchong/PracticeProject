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
                // 没有下一个值，或者下一个值是 flag 时
                if (i + 1 >= commandList.length || isFlag(commandList[i + 1])) {
                    commandMap.put(key.substring(1), null);
                } else {
                    String value = commandList[i + 1];
                    commandMap.put(key.substring(1), value);
                    i++;
                }
            }
        }
    }

    public String getValue(String key) {
        return commandMap.get(key);
    }

    private boolean isNumber(String string) {
        if (string == null) {
            return Boolean.FALSE;
        }
        return pattern.matcher(string).matches();
    }

    /**
     * 是否是 flag
     *      1：以 “-” 开头
     *      2：不是数字
     * @param value
     * @return
     */
    private boolean isFlag(String value) {
        if (value.startsWith("-")) {
            if (isNumber(value)) {
                return false;
            }
            return true;
        }
        return false;
    }
}
