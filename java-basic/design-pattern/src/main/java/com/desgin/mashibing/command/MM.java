package com.desgin.mashibing.command;

public class MM {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void order(Boy b) {
		Command c1 = new ShoppingCommand();
		b.addCommand(c1);
		Command c2 = new HugCommand();
		b.addCommand(c2);
		b.executeCommands();
	}
}
