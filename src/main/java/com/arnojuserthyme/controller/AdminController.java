package com.arnojuserthyme.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arnojuserthyme.model.UserDtls;
import com.arnojuserthyme.service.UserServiceImpl;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserServiceImpl service;
	
	@ModelAttribute
	private void userDetails(Model model) {

		model.addAttribute("users", service.findAdmin()); 

	}
	@GetMapping("/" )
	public String home()
	{
		return "admin/home";
	}
	 @GetMapping("/showAdminUpdate/{id}") 
	public String showAdminUpdate(@PathVariable(value = "id") int id,Model model)
	{
		 UserDtls userDtls = service.getUserById(id);
		 
		 model.addAttribute("user",userDtls);
		 
		return"admin/update_user";
	}
	 @GetMapping("/showAdminDelete/{id}") 
		public String showAdminDelete(@PathVariable(value = "id") int id,Model model)
		{
		 UserDtls userDtls = service.getUserById(id);
		 
		 model.addAttribute("user",userDtls);
			return"admin/delete_user";
		}
	 @PostMapping("/saveUser")
	 public String saveUser(@ModelAttribute("user" )UserDtls userDtls)
	 {
		 service.updateuser(userDtls.getId() , userDtls);
		 
		 return"redirect:/admin/";
	 }
	 @PostMapping("/deleteUser/")
	 public String deleteUser(@ModelAttribute("user" )UserDtls userDtls)
	 {
		 service.deleteUser(userDtls.getId());
		 
		 return"redirect:/admin/";
	 }
}
