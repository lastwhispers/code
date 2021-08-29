package com.desgin.mashibing.command;

public class HugCommand extends Command {

	@Override
	public void execute() {
		System.out.println("hug");
	}

	@Override
	public void unDo() {
		System.out.println("open your arms");
	}

}
