package cn.lastwhisper.jdk5.feature.apt.pizza;


import cn.lastwhisper.jdk5.feature.apt.Factory;

@Factory(type=Tiramisu.class, id="Tiramisu")
public class Tiramisu implements Meal{
	@Override
	public float getPrice() {
		return 4.5f;
	}
}