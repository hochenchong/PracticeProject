package behavioral.command;

import behavioral.command.handler.WindowHandler;

/**
 * 最小化命令类
 *
 * @author hochenchong
 * @date 2024/07/07
 */
public class MinimizeCommand extends Command {
    private final WindowHandler windowHandler = new WindowHandler();

    @Override
    public void execute() {
        windowHandler.minimize();
    }
}
