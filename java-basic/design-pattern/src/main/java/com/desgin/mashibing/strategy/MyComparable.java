package com.desgin.mashibing.strategy;

//使一个类本身具备比较性
//优点：实现此接口的类可以自己重写自己规定比较的内容
//缺点：因为只有一个方法，一个类只能有一种方式进行比较
public interface MyComparable<E> {
	public int compareTo(E e);
}