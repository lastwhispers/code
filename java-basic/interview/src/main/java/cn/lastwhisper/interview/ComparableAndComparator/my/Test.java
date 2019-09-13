package cn.lastwhisper.interview.ComparableAndComparator.my;

public class Test {
	public static void main(String[] args) {
		Person[] ii={new Person(4),new Person(7),new Person(2),new Person(9)};
		DataSort.sort(ii);
//		System.out.println(Arrays.toString(ii));
		for(Person p:ii){
			System.out.println(p.getAge());
		}
	}
}