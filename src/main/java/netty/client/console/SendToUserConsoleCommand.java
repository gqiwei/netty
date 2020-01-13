package netty.client.console;

import io.netty.channel.Channel;
import netty.client.packet.MessageRequestPacket;

import java.util.Scanner;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-13
 */
public class SendToUserConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner sc, Channel channel) {
        String toUserId = sc.nextLine();
        String message = sc.nextLine();
        MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
        messageRequestPacket.setUserId(toUserId);
        messageRequestPacket.setMessage(message);
        channel.writeAndFlush(messageRequestPacket);
    }
}
