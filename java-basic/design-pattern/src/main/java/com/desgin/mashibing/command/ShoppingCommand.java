package com.desgin.mashibing.command;

public class ShoppingCommand extends Command {

	@Override
	public void execute() {
		System.out.println("zoo");
	}

	@Override
	public void unDo() {
		System.out.println("undo zoo");
	}

}
