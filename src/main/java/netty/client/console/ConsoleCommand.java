package netty.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-13
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
