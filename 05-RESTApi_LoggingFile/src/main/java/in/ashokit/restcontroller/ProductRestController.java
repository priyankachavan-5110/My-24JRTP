package in.ashokit.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestController {
	Logger logger=LoggerFactory.getLogger(ProductRestController.class);
	
	@GetMapping("/product")
	public String getProductInfo()
	{
		logger.info("getProductInfo() execution start");
		String msg=null;
		msg.length();//here exception NullPointer Exp will occur
		logger.info("getProductInfo() execution end");
		
		return "Product not foubd";
	}
	
}
