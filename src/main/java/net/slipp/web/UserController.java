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
import net.slipp.domain.QuestionRepository;
import net.slipp.domain.User;
import net.slipp.domain.UserRepository;
import net.slipp.domain.Question;

@Controller
@RequestMapping("/users")
public class UserController{
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/loginForm")
	public String loginForm(){
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session){
		
		User user=userRepository.findByUserId(userId);
		
		if(user==null){
			System.out.println("Login Failure!");
			return "redirect:/users/loginForm";	
		}
		
		if(!user.matchPassword(password)){
			System.out.println("Login Failure!");
			return "redirect:/users/loginForm";
		}
		
		System.out.println("Login success!");		
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY,user);
		return "redirect:/";
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session){
		
		session.removeAttribute("sessionedUser");		
		return "redirect:/";
		
	}
	
	@GetMapping("/form")
	public String form(){
		return "/user/form";
	}
	
	@PostMapping("")
	public String create(User userdata){
		
		System.out.println("user : "+userdata);
		userRepository.save(userdata);
		return "redirect:/users";
		
	}
	
	@GetMapping("")
	public String list(Model model){
		model.addAttribute("users",userRepository.findAll());
		return "/user/list";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model,HttpSession session){
		
		if(!HttpSessionUtils.isLoginUser(session)){
		    return "redirect:/users/loginForm";
		}
		
		User sessionedUser=HttpSessionUtils.getUserFromSession(session);
		
		if(!sessionedUser.matchId(id)){
			throw new IllegalStateException("자신의 정보만 수정할 수 있습니다.");
		}
		
		model.addAttribute("user",userRepository.findById(id).get());
		return "/user/updateForm";
		
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id,User updatedUser){
		
		User user=userRepository.findById(id).get();
		user.update(updatedUser);
		userRepository.save(user);
		return "user/notification";
		
		
	}
	
	
	
	
	
}
