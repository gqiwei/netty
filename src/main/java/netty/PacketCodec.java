package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import netty.client.packet.LoginRequestPacket;
import netty.client.packet.MessageRequestPacket;
import netty.serialize.JSONSerializer;
import netty.serialize.Serializer;
import netty.server.packet.LoginResponsePacket;
import netty.server.packet.MessageResponsePacket;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-08
 */
public class PacketCodec {
    private static final int MAGIC_NUMBER = 0x12345678; //魔数
    public static final PacketCodec INSTANCE = new PacketCodec();

    private final Map<Byte,Class<? extends Packet>> packetTypeMap;
    private final Map<Byte,Serializer> serializerMap;

    public PacketCodec(){
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlogrithm(),serializer);
    }

    /**
     * 数据编码
     * @param packet
     * @return
     */
    public ByteBuf encode(Packet packet){
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();

        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        //协议   魔数+版本号+序列化算法+指令类型+发送信息长度+发送信息

        byteBuf.writeInt(MAGIC_NUMBER);//写入魔数
        byteBuf.writeByte(packet.getVersion());//写入版本号
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlogrithm());//写入序列化算法
        byteBuf.writeByte(packet.getCommand());//写入指令类型
        byteBuf.writeInt(bytes.length);//写入发送信息的长度
        byteBuf.writeBytes(bytes);//写入消息体

        return byteBuf;
    }

    /**
     * 数据解码
     * @return
     */
    public Packet decode(ByteBuf byteBuf){
        byteBuf.skipBytes(4);//跳过魔数，四个字节
        byteBuf.skipBytes(1);//跳过版本号，一个字节
        byte serializerAlogrithm = byteBuf.readByte();//获取序列化算法
        byte packCommand = byteBuf.readByte();//获取指令
        int length = byteBuf.readInt();//获取消息长度
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);//获取消息

        Serializer serializer = getSerializer(serializerAlogrithm);
        Packet packet = serializer.deserialize(getPacket(packCommand),bytes);

        return packet;
    }


    private Serializer getSerializer (byte key){
        return serializerMap.get(key);
    }

    private Class<? extends Packet> getPacket(byte key){
        return packetTypeMap.get(key);
    }
}
