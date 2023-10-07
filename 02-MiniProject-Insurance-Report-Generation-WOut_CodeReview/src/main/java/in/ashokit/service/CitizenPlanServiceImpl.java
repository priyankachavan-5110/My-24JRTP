package in.ashokit.service;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.lowagie.text.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.lowagie.text.Document;

import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import in.ashokit.Entity.CitizenPlan;
import in.ashokit.binding.SearchCriteria;
import in.ashokit.repo.CitizenPlanRepo;
import in.ashokit.utils.EmailUtils;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CitizenPlanServiceImpl implements CitizenPlanService{
	
	@Autowired
	private CitizenPlanRepo repo;
	@Autowired
	private EmailUtils emailUtil;

	@Override
	public List<String> getPlanNames() {
		
		return repo.getPlanNames();
	}

	@Override
	public List<String> getPlanStatus() {
		
		return repo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> searchCitizens(SearchCriteria criteria) {
		CitizenPlan entity=new CitizenPlan();
		/*
		 * if(null!=criteria.getPlanName()&& !"".equals(criteria.getPlanName())) {
		 * entity.setPlanName(criteria.getPlanName()); }
		 */
	//data filtering
		if(StringUtils.isNotBlank(criteria.getPlanName()))//if planName not empty,not null then
		{
			entity.setPlanName(criteria.getPlanName());//set PlanName to criteria
		}
		
		if(StringUtils.isNotBlank(criteria.getPlanStatus()))
		{
			entity.setPlanStatus(criteria.getPlanStatus());
		}
		
		if(StringUtils.isNotBlank(criteria.getGender()))
		{
			entity.setGender(criteria.getGender());
		}
		
		if(null!=criteria.getPlanStartDate())
		{
			entity.setPlanStartDate(criteria.getPlanStartDate());
		}
		
		if(null!=criteria.getPlanEndDate())
		{
			entity.setPlanEndDate(criteria.getPlanEndDate());
		}
		//QBE Query By Example.construct query dynamically based on data available in Entity(f entity contain PlanName then execute WHERE clause in query )
		Example<CitizenPlan> of =Example.of(entity);  //used to filter the data based on Entity obj
		return repo.findAll(of);
	} 
	
	@Override
	public void generateExcel(HttpServletResponse response) throws Exception {
		
		List<CitizenPlan> records = repo.findAll();
		HSSFWorkbook workbook=new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Data");
		
		HSSFRow headerRow = sheet.createRow(0);
		
		// header row creation
		headerRow.createCell(0).setCellValue("S.No");
		headerRow.createCell(1).setCellValue("Name");
		headerRow.createCell(2).setCellValue("Email");
		headerRow.createCell(3).setCellValue("Mobile Number");
		headerRow.createCell(4).setCellValue("SSN");
		headerRow.createCell(5).setCellValue("Gender");
		headerRow.createCell(6).setCellValue("Plan Name");
		headerRow.createCell(7).setCellValue("Plan Status");
		
		//data row creation
		int  rowIndex=1;    //row index start from 1
		for(CitizenPlan record:records)
		{
			HSSFRow dataRow = sheet.createRow(rowIndex);
			
			dataRow.createCell(0).setCellValue(record.getCitizenId());
			dataRow.createCell(1).setCellValue(record.getName());
			dataRow.createCell(2).setCellValue(record.getEmail());
			dataRow.createCell(3).setCellValue(record.getPhno());
			dataRow.createCell(4).setCellValue(record.getSsn());
			dataRow.createCell(5).setCellValue(record.getGender());
			dataRow.createCell(6).setCellValue(record.getPlanName());
			dataRow.createCell(7).setCellValue(record.getPlanStatus());
			
			rowIndex ++;
		}
		//send file in email attachment
		File f=new File("data.xls");
		FileOutputStream fos=new FileOutputStream(f);
		workbook.write(fos);
		emailUtil.sendEmail(f);
		//To downlode file in browser
		ServletOutputStream outputStream = response.getOutputStream();//from response obj getting outputstream
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
		fos.close();
	}

	@Override
	public void generatePdf(HttpServletResponse response) throws Exception {
		
		Document pdfDoc1 = new Document(PageSize.A4);//this documnt for browser-
		ServletOutputStream outputStream = response.getOutputStream();//after click on button file downlode in bowser
		PdfWriter.getInstance(pdfDoc1,outputStream);
		pdfDoc1.open();
		
		//send pdf attachment
		Document pdfDoc2 = new Document(PageSize.A4);//for email attachment
		File f=new File("data.pdf");
		FileOutputStream fos=new FileOutputStream(f);
		PdfWriter.getInstance(pdfDoc2, fos);
		pdfDoc2.open();
		
		//For Paragraph creation in pdf
		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	    fontTiltle.setSize(20);
	    fontTiltle.setColor(CMYKColor.BLUE);
	    fontTiltle.isBold();
	    Paragraph p = new Paragraph("Citizen Insurance Plan Information",fontTiltle);
	    p.setAlignment(Paragraph.ALIGN_CENTER);
	  
	    pdfDoc1.add(p);//browser downlode
	    pdfDoc2.add(p);//email attach
	    
	   //Table creation in pdf 
	    PdfPTable table = new PdfPTable(8);
	    table.setWidthPercentage(100);
	    table.setWidths(new int[] {2,3,6,4,3,3,4,4});
	    table.setSpacingBefore(5);
	    
	  //table headers
	    PdfPCell cell = new PdfPCell();
	    cell.setBackgroundColor(CMYKColor.GRAY);
	    cell.setPadding(5);
	    Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	    font.setColor(CMYKColor.WHITE);
	    
	    cell.setPhrase(new Phrase("S.No", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Name", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Email", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Mobile No", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("SSN", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Gender", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Plan Name", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("Plan Status", font));
	    table.addCell(cell);
	   
	   //create table Data cell
	    List<CitizenPlan> records = repo.findAll();
	    for(CitizenPlan record:records)
		{
	    	table.addCell(String.valueOf(record.getCitizenId()));
	    	table.addCell(record.getName());
	    	table.addCell(record.getEmail());
	    	table.addCell(String.valueOf(record.getPhno()));
	    	table.addCell(String.valueOf(record.getSsn()));
	    	table.addCell(record.getGender());
	    	table.addCell(record.getPlanName());
	    	table.addCell(record.getPlanStatus());
	    } 
	    pdfDoc1.add(table);
	    pdfDoc2.add(table);
	    
	    pdfDoc1.close();
	    outputStream.close();
	    
	    pdfDoc2.close();
	    fos.close();
	    emailUtil.sendEmail(f);
	    
	    
	}
}
