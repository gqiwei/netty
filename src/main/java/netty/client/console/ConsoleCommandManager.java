package netty.client.console;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-13
 */
public class ConsoleCommandManager implements ConsoleCommand{
    private Map<String,ConsoleCommand> consoleCommandMap;

    public  ConsoleCommandManager(){
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("createGroup",new CreateGroupConsoleCommand());
        consoleCommandMap.put("login",new LoginConsoleCommand());
        consoleCommandMap.put("sendToUser",new SendToUserConsoleCommand());
        consoleCommandMap.put("joinGroup",new JoinGroupConsoleCommand());
        consoleCommandMap.put("quitGroup",new QuitGroupConsoleCommand());
        consoleCommandMap.put("listGroupMembers",new ListGroupMembersConsoleCommand());
    }


    @Override
    public void exec(Scanner scanner, Channel channel) {
        String command =scanner.nextLine();
        ConsoleCommand consoleCommand = consoleCommandMap.get(command);
        if(consoleCommand!=null){
            consoleCommand.exec(scanner,channel);
        }else {
            System.out.println("指令不存在");
        }
    }
}
