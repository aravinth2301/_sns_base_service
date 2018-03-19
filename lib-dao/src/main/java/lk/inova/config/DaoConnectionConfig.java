package lk.inova.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lk.inova.dto.entity.Permission;
import lk.inova.dto.entity.Role;
import lk.inova.dto.entity.RolePermission;
import lk.inova.dto.entity.User;

@Configuration
@EnableTransactionManagement
public class DaoConnectionConfig {

	@Value(value = "${admin.hibernate.connection.url}")
	public String url;

	@Value(value = "${admin.hibernate.connection.username}")
	public String username;

	@Value(value = "${admin.hibernate.connection.password}")
	public String password;

	@Value(value = "${admin.hibernate.connection.dialect}")
	public String dialect;

	@Value(value = "${admin.hibernate.connection.driver}")
	public String driver;

	@Value(value = "${admin.hibernate.hbm2ddl.auto}")
	public String dbUpdate;

	@Value(value = "${admin.hibernate.show_sql}")
	public String show_sql;
	//
	@Autowired
	@Qualifier(value = "localSession")
	private LocalSessionFactoryBean sessionFactory;

	@Bean(name = "localSession")
	// @Bean
	// @Qualifier(value = "localSession")
	public LocalSessionFactoryBean localSessionFactoryBean() {

		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		sessionFactory.setDataSource(dataSource());

		Properties hibernateProperties = new Properties();

		hibernateProperties.setProperty("hibernate.dialect", dialect);
		// hibernateProperties.setProperty("hibernate.connection.driver_class", driver);
		// hibernateProperties.setProperty("hibernate.connection.url", url);
		// hibernateProperties.setProperty("hibernate.connection.username", username);
		// hibernateProperties.setProperty("hibernate.connection.password", password);
		hibernateProperties.setProperty("hibernate.hibernate.show_sql", "true");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", dbUpdate);

		/**
		 * don't add any auto config for table create
		 */
		sessionFactory.setHibernateProperties(hibernateProperties);
		sessionFactory.setAnnotatedClasses(Role.class, User.class, Permission.class,RolePermission.class);
		// sessionFactory.setAnnotatedClasses(User.class);
		return sessionFactory;
	}

//	@Bean(name = "transactionManager")
//	public HibernateTransactionManager getTransactionManager() {
//		HibernateTransactionManager txnMgr = new HibernateTransactionManager((SessionFactory) sessionFactory);
//		return txnMgr;
//	}

	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		return dataSource;
	}

	@Bean
	public PlatformTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory.getObject());
		return transactionManager;
	}

}
