package lk.inova.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lk.inova.dao.PermissionDao;
import lk.inova.dto.RequestDTO;
import lk.inova.dto.ResponseDTO;
import lk.inova.exception.DAOException;
import lk.inova.txn.TxnPermissionservice;
import lk.inova.txn.TxnUserservice;
import lk.inova.util.SERVICE_REST_URL;

@RestController
public class RestPermissionImpl {

	private static final Logger log = Logger.getLogger(RestPermissionImpl.class);
	
	@Autowired
	private TxnPermissionservice txnPermissionService;
	
	@RequestMapping(value = SERVICE_REST_URL.ADD_PERMISSION, method = { RequestMethod.POST })
	public @ResponseBody Object addPermission(@RequestBody RequestDTO requestDTO) {
		if (log.isDebugEnabled()) {
			log.debug("added permission");
		}
		try {
			txnPermissionService.addPermissionWithTxn(requestDTO.getPermission());
		} catch (DAOException e) {
			log.error(e);
			return new ResponseDTO(false, e.getErrorMessage());
		}catch (DataIntegrityViolationException e) {
			log.error(e);
			return new ResponseDTO(false, e.getMessage());
		}
		
		return new ResponseDTO(true, "Saved successfully");
	}
	@RequestMapping(value = SERVICE_REST_URL.EDIT_PERMISSION, method = { RequestMethod.POST })
	public @ResponseBody Object editPermission(@RequestBody RequestDTO requestDTO) {
		if (log.isDebugEnabled()) {
			log.debug("edit permission");
		}
		try {
			txnPermissionService.editPermissionWithTxn(requestDTO.getPermission());
		} catch (DAOException e) {
			log.error(e);
			return new ResponseDTO(false, e.getErrorMessage());
		}catch (DataIntegrityViolationException e) {
			log.error(e);
			return new ResponseDTO(false, e.getMessage());
		}
		return new ResponseDTO(true, "Edit successfully");
	}
}
