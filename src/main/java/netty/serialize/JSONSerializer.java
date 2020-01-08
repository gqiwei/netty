package netty.serialize;

import com.alibaba.fastjson.JSON;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-08
 */
public class JSONSerializer implements Serializer{

    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm.JOSN;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
