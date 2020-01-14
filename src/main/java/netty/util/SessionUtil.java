package netty.util;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import netty.attribute.Attributes;
import netty.session.Session;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-13
 */
public class SessionUtil {
    private static final Map<String, Channel> userIdChannelMap = new HashMap<String,Channel>();
    private static final Map<String, ChannelGroup> channelGroupMap =new HashMap<String,ChannelGroup>();

    public static void bindSession(Session session, Channel channel){
        userIdChannelMap.put(session.getUserId(),channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel){
        if(hasLogin(channel)){
            userIdChannelMap.remove( getSession(channel).getUserId());
        }
//        userIdChannelMap.remove(session.getUserId());
    }



    public static boolean hasLogin(Channel channel){
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel){
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId){
        return userIdChannelMap.get(userId);
    }

    public static void bindChannelGroup(String groupId,ChannelGroup channels){
        channelGroupMap.put(groupId,channels);
    }

    public static ChannelGroup getChannelGroup(String groupId){
        return channelGroupMap.get(groupId);
    }

    public static void unBindChannelGroup(String groupId){
        channelGroupMap.remove(groupId);
    }
}
