package com.desgin.pattern.behavioral.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 命令的发起者
 */
public class Staff {
    /**
     * 持有真正执行命令对象的引用
     */
    private List<Command> commandList = new ArrayList<>();

    public void addCommand(Command command) {
        commandList.add(command);
    }

    public void executeCommands() {
        for (Command command : commandList) {
            //请求者调用命令对象执行命令的那个execute方法
            command.execute();
        }
        commandList.clear();
    }
}
