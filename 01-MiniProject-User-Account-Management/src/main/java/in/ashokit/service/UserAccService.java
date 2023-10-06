package in.ashokit.service;

import java.util.List;

import in.ashokit.binding.UserAccount;

public interface UserAccService {
	public String saveOrUpdate(UserAccount userAcc);
	public List<UserAccount>getAllUserAccounts();
	public  UserAccount getUserAcc(Integer userId);
	public boolean deleteUserAcc(Integer userId);//delete record  from DB parmanently
	public boolean updateUserAccStatus(Integer userId,String status); //Active deactive user
	
	
	
	
	
	
	


}
