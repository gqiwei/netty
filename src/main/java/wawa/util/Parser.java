package wawa.util;

import io.netty.buffer.ByteBuf;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-15
 */
public class Parser {
    public static int num=1;
    public static int getPackLength(byte[] bytes){
        return bytes[6];
    }

    public static void parsing(byte[] data){
        System.out.println("命令头："+data[0]);
        int length = data[6]-8;
        byte[] body = new byte[length];
        System.arraycopy(data,7,body,0,length);
        int opId = body[0];
        System.out.println(opId);
    }

    /**
     * 写入头信息
     * @param encoded
     * @param length
     */
    public static void WriteHead(ByteBuf encoded, int length) {
        num++;
        int top = num/256;
        int low = num%256;
        encoded.writeByte(0xfe);
        encoded.writeByte(top);
        encoded.writeByte(low);
        encoded.writeByte(~0xfe);
        encoded.writeByte(~top);
        encoded.writeByte(~low);
        encoded.writeByte(length);
    }

    public static void SetCheckSum(ByteBuf buf){

        buf.resetReaderIndex();
        byte[] data = new byte[buf.readableBytes()];
        buf.readBytes(data);
        int sum = 0;
        for (int i=6;i<data.length;i++){
            sum+= data[i];
        }
        sum = sum%100;
        buf.writeByte(sum);
        buf.resetReaderIndex();
    }
}
