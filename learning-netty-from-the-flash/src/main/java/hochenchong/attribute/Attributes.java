package hochenchong.attribute;

import io.netty.util.AttributeKey;

/**
 * @author hochenchong
 * @date 2024/08/22
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
