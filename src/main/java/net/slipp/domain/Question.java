package net.slipp.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Question{
	
	@Id
	@GeneratedValue
	private Long id; 
	 
	//로그인한 사용자의 정보가지고있음
	//private User user;//이게 객체지향적인 방법입:User와 Question이 협력
	
	//private Long writerId no
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_question_writer"))
	private User writer;
	private String title;
	private String contents;
	private LocalDateTime createDate;
	
	
	@OneToMany(mappedBy="question")
	@OrderBy("id ASC")
	private List<Answer> answers;
	
	//default 생성자
	public Question(){}
	
	//생성자
	public Question(User writer, String title, String contents){
		
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createDate=LocalDateTime.now();
		
	}
	
	public String getFormattedCreateDate() {
		
		if(createDate==null) {
			return "";
		}
		
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
		
		
	}
	
	public void update(String title2, String contents2) {
		this.title=title2;
		this.contents=contents2;
	}
	
	public boolean isSameWriter(User loginUser) {
		return this.writer.equals(loginUser);
	}
	
	
		

}