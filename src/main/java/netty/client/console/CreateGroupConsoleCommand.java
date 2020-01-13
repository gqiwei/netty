package netty.client.console;

import io.netty.channel.Channel;
import netty.client.packet.CreateGroupRequestPacket;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-13
 */
public class CreateGroupConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入需要加入群组的用户ID");
        String userIdList =scanner.nextLine();
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        createGroupRequestPacket.setUserIdList(Arrays.asList(userIdList.split(",")));
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
