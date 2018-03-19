package lk.inova.view.beans;

import java.util.Date;

import javax.annotation.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

@ManagedBean
@ViewScoped
public class IndexBean {

	private static final Logger log = Logger.getLogger(IndexBean.class);

	private String welcomeMessage = "Populated by JSF ";
	private String value1;
	private String value2;

	public String getWelcomeMessage() {
		return welcomeMessage + new Date();
	}

	public void indexSubmit() {

		log.info("Entered values are " + value1 + " " + value2);
		//return "#";
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

}
