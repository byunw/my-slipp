package net.slipp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String writer;
	private String title;
	private String contents;
	
	
	//default 생성자
	public Question(){}
	
	
	//생성자
	public Question(String writer, String title, String contents){
		
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		
	}
	

}