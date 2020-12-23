package net.slipp.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	@GetMapping("/helloworld")
	public String welcome(Model model){
		
		List<Person> people=Arrays.asList(new Person("alex"),new Person("dan"));
		model.addAttribute("people",people);
		return "welcome";
		
	}
	
}
