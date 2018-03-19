package lk.inova.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lk.inova.dto.RequestDTO;
import lk.inova.dto.ResponseDTO;
import lk.inova.txn.TxnUserservice;
import lk.inova.util.SERVICE_REST_URL;

@RestController
public class RestUserImpl {

	private static final Logger log = Logger.getLogger(RestUserImpl.class);
	
	@Autowired
	private TxnUserservice txnUserservice;
	
	@RequestMapping(value = SERVICE_REST_URL.ADD_USER, method = { RequestMethod.POST })
	public @ResponseBody Object addUser(@RequestBody RequestDTO requestDTO) {
		if (log.isDebugEnabled()) {
			log.debug("added User");
		}
		
		try {
			txnUserservice.addUserWithTxn(requestDTO.getUser());
		} catch (DataIntegrityViolationException e) {
			log.error(e);
			return new ResponseDTO(false, e.getMessage());
		} catch (Exception e) {
			log.error(e);
			return new ResponseDTO(false, e.getMessage());
		}
		return new ResponseDTO(true, "Saved successfully");
	}
	@RequestMapping(value = SERVICE_REST_URL.EDIT_USER, method = { RequestMethod.POST })
	public @ResponseBody Object editUser(@RequestBody RequestDTO requestDTO) {
		if (log.isDebugEnabled()) {
			log.debug("edit User");
		}
		try {
			txnUserservice.editUserWithTxn(requestDTO.getUser());
		} catch (DataIntegrityViolationException e) {
			log.error(e);
			return new ResponseDTO(false, e.getMessage());
		} catch (Exception e) {
			log.error(e);
			return new ResponseDTO(false, e.getMessage());
		}
		return new ResponseDTO(true, "edited successfully");
	}
}
