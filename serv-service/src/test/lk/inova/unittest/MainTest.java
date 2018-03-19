package lk.inova.unittest;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import lk.inova.dto.RequestDTO;
import lk.inova.dto.ResponseDTO;
import lk.inova.dto.entity.Permission;
import lk.inova.dto.entity.Role;
import lk.inova.dto.entity.User;
import lk.inova.util.SERVICE_REST_URL;

@RunWith(SpringRunner.class)
public class MainTest {

	private RestTemplate restTemplate = new RestTemplate();

	private static final String BASE_URL = "http://localhost:9002";
	private static final Logger log = Logger.getLogger(MainTest.class);

	public void testMethod() {
		log.info("test method");
	}
	// ============================Start Permission =============================================================
	public void testAddPermission() {

		RequestDTO request = new RequestDTO();
		Permission permission = new Permission();
		permission.setCode("P1");
		permission.setCode("Permission 1");
		request.setPermission(permission);

		restTemplate.postForObject(BASE_URL + SERVICE_REST_URL.ADD_PERMISSION, request, ResponseDTO.class);
	}
	
	public void testSearchAndEditPermission() {

		RequestDTO request = new RequestDTO();
		Permission permission = new Permission();
		
		
		permission.setCode("P1");
		permission.setCode("Permission 1");
		request.setPermission(permission);

		restTemplate.postForObject(BASE_URL + SERVICE_REST_URL.ADD_PERMISSION, request, ResponseDTO.class);
	}

	// ============================End Permission =============================================================
	
	// ============================Start Role =================================================================
	public void testAddRole() {

		RequestDTO request = new RequestDTO();
		Role role = new Role();
		role.setCode("R1");
		role.setCode("Role 1");
		request.setRole(role);

		restTemplate.postForObject(BASE_URL + SERVICE_REST_URL.ADD_ROLE, request, ResponseDTO.class);
	}
	
	public void testSearchAndEditRole() {

		RequestDTO request = new RequestDTO();
		Role role = new Role();
		role.setCode("R1");
		role.setCode("Role 1");
		request.setRole(role);

		restTemplate.postForObject(BASE_URL + SERVICE_REST_URL.ADD_ROLE, request, ResponseDTO.class);
	}

	// ============================End Role =================================================================
	
	// ============================Start User ===============================================================
	public void testUser() {

		RequestDTO request = new RequestDTO();
		User user = new User();
		user.setUsername("aaa11a");
		user.setPassword("password");
		//user.setCode("U01");
		Role role = new Role();
		role.setId(3l);
		//user.setRole(role);

		request.setUser(user);
		log.info(user);
		log.info(restTemplate.postForObject(BASE_URL + SERVICE_REST_URL.ADD_USER, request, ResponseDTO.class));
	}
	
	
	public void testSearchAndEditUser() {

		RequestDTO request = new RequestDTO();
		User user = new User();
		user.setUsername("aaa11a");
		user.setPassword("password");
		//user.setCode("U01");
		Role role = new Role();
		role.setId(3l);
		//user.setRole(role);

		request.setUser(user);
		log.info(user);
		log.info(restTemplate.postForObject(BASE_URL + SERVICE_REST_URL.ADD_USER, request, ResponseDTO.class));
	}
	// ============================End User =================================================================
}
