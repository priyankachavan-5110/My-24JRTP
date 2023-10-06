package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.Entity.CitizenPlan;
import in.ashokit.binding.SearchCriteria;
import in.ashokit.service.CitizenPlanService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CitizenPlanController {
	@Autowired
	private CitizenPlanService service;
	
	@GetMapping("/")
	public String index(Model model)
	{
		formInitCommonCode(model);
		model.addAttribute("search",new SearchCriteria());
		return "index";
	}
	//Load data for the drop down
	private void formInitCommonCode(Model model) {                            //duplicate code we extract in one method
		List<String> planNames = service.getPlanNames();
		List<String> planStatus = service.getPlanStatus();
		model.addAttribute("plannamelist",planNames);                         //send data to populate drop down list
		model.addAttribute("planstatuslist", planStatus);
	}
	
	@PostMapping("/filter-data")
	public String handleSearchBtn(@ModelAttribute("search")SearchCriteria criteria,Model model)//
	{
		System.out.println(criteria);
		List<CitizenPlan> citizensInfo = service.searchCitizens(criteria);
		model.addAttribute("citizens", citizensInfo);//List of all records
		formInitCommonCode(model);
		return "index";
	}
	
	@GetMapping("/excel")                                                     //this method downlode table data in excel file
	public void downlodeExcel(HttpServletResponse response) throws Exception
	{
		response.setContentType("application/octet-stream");
		
		String headerKey="Content-Disposition";
		String headerValue="attachment;filename=data.xlsx";
		response.addHeader(headerKey,headerValue);
		
		service.generateExcel(response);
	}
	
	@GetMapping("/pdf")
	public void downlodePdf(HttpServletResponse response) throws Exception
	{
		 response.setContentType("application/pdf");
		 
		 String headerKey = "Content-Disposition";
		 String headerValue = "attachment; filename=data.pdf";
		 response.setHeader(headerKey, headerValue);
		
		service.generatePdf(response);
	}
	
}
