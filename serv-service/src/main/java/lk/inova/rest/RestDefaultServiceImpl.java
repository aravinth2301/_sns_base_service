package lk.inova.rest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lk.inova.dto.ResponseDTO;

@RestController
public class RestDefaultServiceImpl {
	private static final Logger log = Logger.getLogger(RestDefaultServiceImpl.class);

	@RequestMapping(value = "/*", method = { RequestMethod.GET })
	public Object welComes() {
		if (log.isDebugEnabled()) {
			log.debug("Method called");
		}
		return new ResponseDTO(true, "Hi Welcome");
	}
}
