package lk.inova.view.beans;

import java.util.Date;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class JsfBean {

	private String welcomeMessage = "Populated by JSF ";

	public String getWelcomeMessage() {
		return welcomeMessage + new Date();
	}

}
