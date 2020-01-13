package netty.client.console;

import io.netty.channel.Channel;
import netty.client.packet.LoginRequestPacket;

import java.util.Scanner;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-13
 */
public class LoginConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner sc, Channel channel) {
        System.out.println("请输入账号");
        String userName = sc.nextLine();

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setPassword("123456");
        loginRequestPacket.setUsername(userName);
        channel.writeAndFlush(loginRequestPacket);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {

        }
    }
}
