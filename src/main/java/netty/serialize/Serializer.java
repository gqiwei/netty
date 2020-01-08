package netty.serialize;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description 序列化接口
 * @date 2020-01-08
 */
public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 获取序列化算法
     * @return
     */
    byte getSerializerAlogrithm();

    /**
     * java 对象转换成二进制
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 二进制转成java对象
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz,byte[] bytes);
}
