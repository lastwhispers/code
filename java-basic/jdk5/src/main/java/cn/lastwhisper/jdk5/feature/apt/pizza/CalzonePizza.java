package cn.lastwhisper.jdk5.feature.apt.pizza;


import cn.lastwhisper.jdk5.feature.apt.Factory;

@Factory(type=CalzonePizza.class, id="Calzone")
public class CalzonePizza implements Meal{
	@Override
	public float getPrice() {
		return 8.5f;
	}
}