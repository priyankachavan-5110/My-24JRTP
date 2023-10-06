package in.ashokit.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.ashokit.Entity.CitizenPlan;
import in.ashokit.repo.CitizenPlanRepo;


//this runner class execute only one time when application is start.it load dumy data into table
@Component
public class DataLoader implements ApplicationRunner
{
	@Autowired
	private CitizenPlanRepo repo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception //this run method execute
	{
		repo.deleteAll(); //first it will delete all record from table and then it insert record.so duplicate data will not insert
		
		CitizenPlan p1=new CitizenPlan("Ram","ram@gmail.com",234242l,143123l,"Male","Cash","Approved",LocalDate.now(),LocalDate.now().plusMonths(6));
		
		CitizenPlan p2=new CitizenPlan("John","john@gmail.com",234542l,3453123l,"Male","Cash","Completed",LocalDate.now(),LocalDate.now().plusMonths(6));
		
		CitizenPlan p3=new CitizenPlan("smith","smith@gmail.com",234242l,143123l,"Male","Cash","Denied",null,null);//for denied start & end date null 
		
		CitizenPlan p4=new CitizenPlan("jyoti","jyoti@gmail.com",43442l,67523l,"Male","Food","Approved",LocalDate.now(),LocalDate.now().plusMonths(6));
		
		CitizenPlan p5=new CitizenPlan("Geb","geb@gmail.com",234242l,143123l,"Male","Medical","Denied",null,null);
		
		CitizenPlan p6=new CitizenPlan("Riya","riya@gmail.com",94578l,3323l,"Female","cash","Denied",null,null);
		
		CitizenPlan p7=new CitizenPlan("Andy","andy@gmail.com",98242l,234123l,"Male","Medical","Completed",LocalDate.now(),LocalDate.now().plusMonths(6));
		
		CitizenPlan p8=new CitizenPlan("Rayan","rayan@gmail.com",90242l,473123l,"Male","Food","Denied",null,null);
		
		CitizenPlan p9=new CitizenPlan("jehu","jehu@gmail.com",289657l,144343l,"Male","cash","Denied",null,null);
		
		CitizenPlan p10=new CitizenPlan("Robert","robert@gmail.com",98742l,320123l,"Male","Medical","Completed",LocalDate.now(),LocalDate.now().plusMonths(6));
		
		CitizenPlan p11=new CitizenPlan("Alice","alice@gmail.com",700557l,809743l,"Female","Food","Denied",null,null);
		
		CitizenPlan p12=new CitizenPlan("Gine","gine@gmail.com",798052l,400471l,"Female","Travel","Approved",LocalDate.now(),LocalDate.now().plusMonths(6));

		CitizenPlan p13=new CitizenPlan("Lili","lili@gmail.com",6542l,23423l,"Female","Medical","Denied",null,null);
		
		CitizenPlan p14=new CitizenPlan("Ross","ross@gmail.com",6552l,13371l,"Male","Vehicle","Approved",LocalDate.now(),LocalDate.now().plusMonths(6));

		CitizenPlan p15=new CitizenPlan("Rick","rick@gmail.com",7982l,78823l,"male","Travel","Denied",null,null);
		
		CitizenPlan p16=new CitizenPlan("Violet","violet@gmail.com",798052l,400471l,"Female","Home","Approved",LocalDate.now(),LocalDate.now().plusMonths(6));

		CitizenPlan p17=new CitizenPlan("Flora","flora@gmail.com",6542l,23423l,"Female","Vehicle","Denied",null,null);
		
		CitizenPlan p18=new CitizenPlan("Rose","rose@gmail.com",6542l,23423l,"Female","Home","Denied",null,null);
		
		List<CitizenPlan>records= Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15,p16,p17,p18);
		repo.saveAll(records);
	}
	

}
 