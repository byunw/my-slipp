package net.slipp.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController{
	
	//회원가입한 사용자를 저장하는 ListCollection
	//users will be empty when web server restarts
	//users=[] b/c the web server was turned off by the command kill -9 process id and is restarted by running jar file inside target
	private List<User> users=new ArrayList<User>();
	
	@PostMapping("/create")
	public String create(User user){
		
		System.out.println("user : "+user);
		users.add(user);
		return "redirect:/list";
		
	}
	
	@GetMapping("/list")
	public String list(Model model){
		
		model.addAttribute("users",users);
		return "list";
		
	}
	
	
	
	
}
