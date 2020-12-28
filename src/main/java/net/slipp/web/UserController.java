package net.slipp.web;

import java.util.ArrayList;
import java.util.List;

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
	
	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}
	
	@PostMapping("")
	public String create(User user){
		
		System.out.println("user : "+user);
		
		//saving a User object in database
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
