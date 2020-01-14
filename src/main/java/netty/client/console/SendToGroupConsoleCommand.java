package netty.client.console;

import io.netty.channel.Channel;
import netty.client.packet.GroupMessageRequestPacket;

import java.util.Scanner;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-14
 */
public class SendToGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner sc, Channel channel) {
        System.out.print("请输入发送群组ID：");
        String groupId = sc.nextLine();
        System.out.print("请输入发送信息：");
        String message = sc.nextLine();
        GroupMessageRequestPacket groupMessageRequestPacket = new GroupMessageRequestPacket();
        groupMessageRequestPacket.setGroupId(groupId);
        groupMessageRequestPacket.setMessage(message);
        channel.writeAndFlush(groupMessageRequestPacket);
    }
}
