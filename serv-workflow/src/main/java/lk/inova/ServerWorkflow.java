package lk.inova;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerWorkflow {

	private static final Logger log = Logger.getLogger(ServerWorkflow.class);

	public static void main(String[] args) {
		SpringApplication.run(ServerWorkflow.class, args);
	}

	// @Bean
	// CommandLineRunner init(final RepositoryService repositoryService, final
	// RuntimeService runtimeService,
	// final TaskService taskService) {
	//
	// return new CommandLineRunner() {
	//
	// public void run(String... strings) throws Exception {
	// Map<String, Object> variables = new HashMap<String, Object>();
	// variables.put("applicantName", "John Doe");
	// variables.put("email", "john.doe@activiti.com");
	// variables.put("phoneNumber", "123456789");
	// runtimeService.startProcessInstanceByKey("my-process", variables);
	// }
	// };
	// }

	@Bean
	InitializingBean usersAndGroupsInitializer(final IdentityService identityService) {

		return new InitializingBean() {
			public void afterPropertiesSet() throws Exception {

				Group group = identityService.newGroup("g-user");
				group.setName("g-users");
				group.setType("security-role");

				try {
					identityService.saveGroup(group);
				} catch (Exception e) {
					log.error(e);
				}

				User admin = identityService.newUser("admin");
				admin.setPassword("admin");
				try {
					identityService.saveUser(admin);
				} catch (Exception e) {
					log.error(e);
				}

			}
		};
	}
}
