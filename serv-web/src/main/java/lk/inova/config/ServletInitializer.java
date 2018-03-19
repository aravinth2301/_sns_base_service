package lk.inova.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

//import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;



@Configuration
public class ServletInitializer extends SpringBootServletInitializer {



	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
		servletContext.setInitParameter("javax.faces.PARTIAL_STATE_SAVING_METHOD", "true");
		servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Production");
		servletContext.setInitParameter("facelets.DEVELOPMENT", "false");
		servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "-1");

	}
	
}
