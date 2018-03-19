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
import lk.inova.exception.DAOException;
import lk.inova.txn.TxnRoleservice;
import lk.inova.util.SERVICE_REST_URL;

@RestController
public class RestRoleImpl {

	private static final Logger log = Logger.getLogger(RestRoleImpl.class);
	
	
	@Autowired
	private TxnRoleservice txnRoleservice;
	
	
	@RequestMapping(value = SERVICE_REST_URL.ADD_ROLE, method = { RequestMethod.POST })
	public @ResponseBody Object addRole(@RequestBody RequestDTO requestDTO) {
		if (log.isDebugEnabled()) {
			log.debug("added Role");
		}
		
		try {
			txnRoleservice.addRoleWithTxn(requestDTO.getRole(),requestDTO.getListRolePermission());
		} catch (DAOException e) {
			log.error(e);
			return new ResponseDTO(false, e.getErrorMessage());
		}catch (DataIntegrityViolationException e) {
			log.error(e);
			return new ResponseDTO(false, e.getMessage());
		}
		
		
		return new ResponseDTO(true, "Saved successfully");
	}
	@RequestMapping(value = SERVICE_REST_URL.EDIT_ROLE, method = { RequestMethod.POST })
	public Object editRole() {
		if (log.isDebugEnabled()) {
			log.debug("edit Role");
		}
		return new ResponseDTO(true, "Saved successfully");
	}
}
