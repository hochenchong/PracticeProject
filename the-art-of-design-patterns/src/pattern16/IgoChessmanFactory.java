package pattern16;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hochenchong
 * @date 2024/07/06
 */
public class IgoChessmanFactory {
    private static final IgoChessmanFactory INSTANCE = new IgoChessmanFactory();
    private static Map<String, IgoChessman> map;

    private IgoChessmanFactory() {
        map = new HashMap<>();
        map.put("b", new BlackIgoChessman());
        map.put("w", new WhiteIgoChessman());
    }

    // 返回唯一实例
    public static IgoChessmanFactory getInstance() {
        return INSTANCE;
    }

    // 根据颜色返回棋子
    public IgoChessman getIgoChessman(String color) {
        return map.get(color);
    }
}
