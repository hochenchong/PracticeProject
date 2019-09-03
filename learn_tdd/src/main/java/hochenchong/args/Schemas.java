package hochenchong.args;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HochenChong
 * @date 2019/09/02
 */
public class Schemas {
    Map<String, String> schemaMap = new HashMap<>();

    public Schemas(String schemaConfig) {
        // 解析传进来的 schema
        String[] schema = schemaConfig.split(" ");
        for (String s : schema) {
            String[] strings = s.split(":");
            schemaMap.put(strings[0], strings[1]);
        }
    }

    public int size() {
        return schemaMap.size();
    }

    public Object getValue(String name, String strValue) {
        switch (schemaMap.get(name)) {
            case "bool" :
                return "true".equalsIgnoreCase(strValue);
            case "int" :
                return Integer.valueOf(strValue);
                default:
                    return strValue;
        }
    }
}
