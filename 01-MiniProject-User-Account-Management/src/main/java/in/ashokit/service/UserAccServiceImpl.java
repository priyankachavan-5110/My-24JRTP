package in.ashokit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.UserAccount;
import in.ashokit.repository.UserAccountRepo;
@Service
public class UserAccServiceImpl implements UserAccService{
	@Autowired
	private UserAccountRepo userAccRepo;
	
	@Override
	public String saveOrUpdate(UserAccount userAcc) {
		Integer userid =userAcc.getUserId();
		if(userid==null)
		{
			userAcc.setActiveSw("Y");
		}
				
		userAccRepo.save(userAcc);
		if(userid==null)
		{
			return "User Data Saved Successfully..";
		}
		return "User Data Updated Successfully..";
	}

	@Override
	public List<UserAccount> getAllUserAccounts() {
		List<UserAccount> userlist= userAccRepo.findAll();
		return userlist;
	}

	@Override
	public UserAccount getUserAcc(Integer userId) {
		Optional<UserAccount> findById = userAccRepo.findById(userId);//optional to handel NPException
		if(findById.isPresent())//check id present or not
		{
			return findById.get();//return record of present id
		}
		return null;
	}

	@Override
	public boolean deleteUserAcc(Integer userId) {
		//userAccRepo.deleteById(userId); parmanently
		boolean existsById = userAccRepo.existsById(userId);
		
		if(existsById)
		{
			userAccRepo.deleteById(userId);
			return true;
		}
		return false;
		
		/*try {
			userAccRepo.deleteById(userId);
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return false;
	}*/
	}

	@Override
	public boolean updateUserAccStatus(Integer userId, String status) //to Active or deactivate account status
	{
		try {
			userAccRepo.updateUserAccStatus(userId, status);
			return true;
			} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
