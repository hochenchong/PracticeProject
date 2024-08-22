package hochenchong.chapter8.serialize;

/**
 * @author hochenchong
 * @date 2024/08/22
 */
public interface Serializer {
    Serializer DEFAULT = new JSONSerializer();
    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * Java 对象转换成二进制数据
     */
    byte[] serialize(Object object);

    /**
     * 二进制数据转换成 Java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
