package cn.lastwhisper.interview.ComparableAndComparator.my;

//人
public class Person implements MyComparable{
	private int age;
 
	public Person(int age) {
		super();
		this.age = age;
	}
 
	public int compareTo(Object e) {
		if(e instanceof Person){
			Person temp=(Person)e;
			if(this.age>temp.getAge())return 1;
			else if(this.age<temp.getAge()) return -1;
			return 0;
		}
		throw new RuntimeException("传递参数错误，比较失败");
	}
 
	public int getAge() {
		return age;
	}
 
	public void setAge(int age) {
		this.age = age;
	}
}