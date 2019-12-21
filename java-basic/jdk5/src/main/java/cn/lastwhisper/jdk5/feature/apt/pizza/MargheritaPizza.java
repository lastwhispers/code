package cn.lastwhisper.jdk5.feature.apt.pizza;

import cn.lastwhisper.jdk5.feature.apt.Factory;

@Factory(type=MargheritaPizza.class, id="Margherita")
public class MargheritaPizza implements Meal{
	@Override
	public float getPrice() {
		return 6.0f;
	}
}