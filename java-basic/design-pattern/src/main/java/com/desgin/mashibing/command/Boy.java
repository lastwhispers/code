package com.desgin.mashibing.command;

import java.util.ArrayList;
import java.util.List;

public class Boy {
	private String name;
	private List<Command> commands = new ArrayList<Command>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void pursue(MM mm) {
	}

	public void doSomeThing() {
		
	}

	public void addCommand(Command c1) {
		this.commands.add(c1);
	}

	public void executeCommands() {
		for(Command c : commands) {
			c.execute();
		}
	}
	
	public void undoCommands() {
		//
	}
	
}
