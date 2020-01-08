package netty.util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import netty.attribute.Attributes;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-08
 */
public class LoginUtil {
    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> attributeKey =channel.attr(Attributes.LOGIN);
        if(attributeKey ==null){
            return false;
        }else if(attributeKey.get()){
            return true;
        }

        return false;
    }
}
