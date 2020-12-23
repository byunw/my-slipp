package net.slipp.web;

//Person이라는 클래스
public class Person {
	
	//Person이라는 클래스는 name이라는 1개의 필드(attribute)를 가지고있
	private String name;
	
	//Person이라는 클래스의 생성자(constructor)
	//new keyword의 parameter가 새로운 Person 객체의 필드값 name의 값이되게해줌
	//예를들어,new Person("n") 일 경우, 새로운 Person 객체의 name값은 "dan"이됨
	public Person(String name) {//name은 new keyword parameter를 의
		
		//이 부분이 해주는거: 객체가 생성될때, 새로운 객체의 name(field)값을 new keyword안에있는 parameter로 해줌
		this.name=name;
		
	}
	
	
}
