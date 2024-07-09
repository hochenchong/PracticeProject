package behavioral.command;

import behavioral.command.handler.HelpHandler;

/**
 * 帮助命令类
 *
 * @author hochenchong
 * @date 2024/07/07
 */
public class HelpCommand extends Command {

    private final HelpHandler helpHandler = new HelpHandler();


    @Override
    public void execute() {
        helpHandler.display();
    }
}
