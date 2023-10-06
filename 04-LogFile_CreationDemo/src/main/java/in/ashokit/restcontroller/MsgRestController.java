package in.ashokit.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsgRestController {
																//by default Spring Boot use Logback to generate Log msg for our application
	Logger logger=LoggerFactory.getLogger(MsgRestController.class);//logger is used to generate log msg for our app
	
	@GetMapping("/welcome")
	public String getWelcomeMsg()
	{
		logger.trace("this is log - trace msg");
		logger.debug("this is log - debug msg");
		logger.info("this is log - info msg"); //In this example it print only info messages(bcz its default level )
		logger.warn("this is log - warn msg");
		logger.error("this is log - error msg");
		String msg="Welcome..!!";
		return msg;
		
	}
	@GetMapping("/greet")
	public String getGreetMsg()
	{
		String msg="Good morning,have a nice day!!";
		return msg;
		
	}

}
