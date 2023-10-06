package in.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.binding.UserAccount;
import jakarta.transaction.Transactional;

public interface UserAccountRepo extends JpaRepository<UserAccount, Integer> {
	@Modifying//modify user record update
	@Transactional //this @ use when custom Query update/delete operation.For predefine method JPA will provide transaction, but for custom we need to write this annotation 
	@Query("update UserAccount set activeSw=:status where userId=:userId")
	public void updateUserAccStatus(Integer userId,String status);

}
 