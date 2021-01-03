package net.slipp.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.slipp.domain.User;
import net.slipp.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController{
	
	//private List<User> users=new ArrayList<User>();
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/loginForm")
	public String loginForm(){
		return "/user/login";
	}
	
	//로그인 기능
	@PostMapping("/login")
	public String login(String userId,String password,HttpSession session) {
		 
		//userId="alex"
		//password="1461"
		
		//사용자가 로그인할때 사용자아이디 박스에 타입한거를 기반으로 database에서 찾은 User object를 user라고 부름
		User user=userRepository.findByUserId(userId);
		
		System.out.println(user.getPassword());
		
		//만약 사용자 아이디 박스에 타입한 정보(사용자 아이디)와 일치하는 object가 database안에 없으면 
		//예를들어, 사용자가 로그인하려고 사용자아이디박스에 cc타입했는데 database안에는 한개의 object만있고 그 object의 userI값은 alex1461
		if(user==null) {
			
			System.out.println("Login Failure!");
			return "redirect:/users/loginForm";
			
		}
		
		//사용자 아이디 박스에 친 아이디와 동일한 userId값을 가지는 User 객체가 있으면 
		// 그 객체의 password값 비빌번호 박스에 타입한 비빌번호가 다르면 로그인 다시하라는 페이지 보여주기
		
		if(!password.equals(user.getPassword())) {
			
			System.out.println("Login Failure!");
			return "redirect:/users/loginForm";

		}
		
		System.out.println("Login success!");
		session.setAttribute("user",user);
		return "redirect:/";
		
	}
	
	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}
	
	@PostMapping("")
	public String create(User user){
		
		System.out.println("user : "+user);		
		userRepository.save(user);
		return "redirect:/users";
		
	}
	
	@GetMapping("")
	public String list(Model model){
		
		//userRepository.findAll() database에 있는 데이터를 담아서 줌
		//what does userRepository.findAll() returns User objects stored in database
		
		//Saving "users":data in database in model 
		
		model.addAttribute("users",userRepository.findAll());
		return "/user/list";
		
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model){
		model.addAttribute("user",userRepository.findById(id).get());//model={"user":User object}
															         //model.addAttribute("user",User object)
		
		return "/user/updateForm";
		
	}
	
	//정보를 수정하는 역할
	//html에서는 http method중: get이랑 post만 사용가능
	@PutMapping("/{id}")
	public String update(@PathVariable Long id,User newUser) {
		
		User user=userRepository.findById(id).get();
		user.update(newUser);
		userRepository.save(user);
		return "redirect:/users";
		
	}
	
	
	
	
}
