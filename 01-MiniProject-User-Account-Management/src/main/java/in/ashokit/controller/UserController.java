package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.binding.UserAccount;
import in.ashokit.service.UserAccService;
import jakarta.validation.Valid;
@Controller
public class UserController {
	@Autowired
	private UserAccService userAccService;
	
 //load form
	@GetMapping("/")
	public String index(Model model)//load register page form
	{
		model.addAttribute("userAccObject",new UserAccount());//1""this key is form binding obj to map form to class variables.//here manually setting model attribute of user with creating UserAccount obj
		return "index";
		
	}
//save user in DB(Form submission)
	@PostMapping("/save-User")
	public String handelSubmiBtn(@ModelAttribute("userAccObject") @Valid UserAccount userAcc,BindingResult result,Model model) //2 Submit user data(save in DB)here we user @ModelAttribute to set user obj to model and send to UI page
		{
		System.out.println(userAcc);
		String msg = userAccService.saveOrUpdate(userAcc);
		model.addAttribute("msg",msg);
		model.addAttribute("userAccObject",new UserAccount());//sending empty binding objuser cant send same form agan
		//return "redirect:/view-AllUsers";//after submit form it direct goes to View users fom
		return "index";  //if we want to print success msg on same page
	}
//POST Request Redirecting to GET request
	
	/*
	 * @GetMapping("/userAccCreationSuccess") public String userAccCreationSuccess()
	 * { return "index"; }//return view name from this GET method rather than POST }
	 */
	
//Show all users
	@GetMapping("/view-AllUsers")
	public String viewUsers(Model model)
	{
		System.out.println("in view users");
		List<UserAccount> userList = userAccService.getAllUserAccounts();
		model.addAttribute("userAccObject",userList);
		return "view_users";
	}
	
//delete
	@GetMapping("/delete")
	public String deleteUser(@RequestParam("userId")Integer userId,Model model)
	{ 
		boolean deleteUserAcc = userAccService.deleteUserAcc(userId);
		model.addAttribute("userAccObject",userId);
		model.addAttribute("msg","User Deleted Sucessully..");
		return "forward:/view-AllUsers";
		
	}
//Edit user
	@GetMapping("/update")
	public String editUser(@RequestParam ("userId")Integer userId,Model model)
	{ 
		UserAccount edituser = userAccService.getUserAcc(userId);
		model.addAttribute("userAccObject", edituser);
		 return "index"; 
	}
	
//status change	
	@GetMapping("/status")
	public String changeAccStatus(@RequestParam ("userId") Integer userId,@RequestParam ("status")String status,Model model)
	{
		userAccService.updateUserAccStatus(userId,status);
		if("Y".equals(status))
		{
		 
		model.addAttribute("msg","Use Account Activated");
		}else
			model.addAttribute("msg","Use Account De-Activated");
		return "forward:/view-AllUsers";
	}
}
