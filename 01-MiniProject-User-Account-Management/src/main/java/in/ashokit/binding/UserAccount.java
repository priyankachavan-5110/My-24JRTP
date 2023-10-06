package in.ashokit.binding;
import java.time.LocalDate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Entity
@Table(name="USER_DTLS")
public class UserAccount {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="USER_ID")
private Integer userId;

@Column(name="FULL_NAME")
private String fullName;

@Column(name="EMAIL_ID",unique=true)
private String email;

@Column(name="PHONE_NO")
private Long phno;

@Column(name="GENDER")
private String gender;

@Column(name="DATE_OF_BIRH")
@DateTimeFormat(pattern ="yyyy-MM-dd")
private LocalDate dob;

@Column(name="SNN")
private Long ssn;

@Column(name="ACTIVE_STATUS")
private String activeSw;

@Column(updatable=false)		//when 1st time insert data update_date should be null 
@CreationTimestamp            //to data jpa Auto generate value.it populate data of creation timestamp
private LocalDate create_date;

@Column(insertable=false)	 //when update data then insert_date should be same 
@UpdateTimestamp
private LocalDate update_date;

public Integer getUserId() {
	return userId;
}
public void setUserId(Integer userId) {
	this.userId = userId;
}
public String getFullName() {
	return fullName;
}
public void setFullName(String fullName) {
	this.fullName = fullName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Long getPhno() {
	return phno;
}
public void setPhno(Long phno) {
	this.phno = phno;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public LocalDate getDob() {
	return dob;
}
public void setDob(LocalDate dob) {
	this.dob = dob;
}
public Long getSsn() {
	return ssn;
}
public void setSsn(Long ssn) {
	this.ssn = ssn;
}
public String getActiveSw() {
	return activeSw;
}
public void setActiveSw(String activeSw) {
	this.activeSw = activeSw;
}
public LocalDate getCreate_date() {
	return create_date;
}
public void setCreate_date(LocalDate create_date) {
	this.create_date = create_date;
}
public LocalDate getUpdate_date() {
	return update_date;
}
public void setUpdate_date(LocalDate update_date) {
	this.update_date = update_date;
}
@Override
public String toString() {
	return "UserAccount [userId=" + userId + ", fullName=" + fullName + ", email=" + email + ", phno=" + phno
			+ ", gender=" + gender + ", dob=" + dob + ", ssn=" + ssn + ", activeSw=" + activeSw + ", create_date="
			+ create_date + ", update_date=" + update_date + "]";
}


}
