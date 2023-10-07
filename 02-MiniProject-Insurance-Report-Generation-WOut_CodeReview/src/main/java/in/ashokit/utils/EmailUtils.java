package in.ashokit.utils;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;
@Component//make class as spring bean
public class EmailUtils {
	@Autowired
	private JavaMailSender mailsender;
	
	
	public boolean sendEmail(File file)
	{
		boolean status=false;
		try
		{
			MimeMessage msg=mailsender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(msg,true);
			
			helper.setTo("priyankchavan5110@gmail.com");
			helper.setSubject("Your Report");
			helper.setText("<h2>Please downlode your report</h2>",true);
			helper.addAttachment(file.getName(),file);
			mailsender.send(msg);
			status=true;
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
		
	}

}
