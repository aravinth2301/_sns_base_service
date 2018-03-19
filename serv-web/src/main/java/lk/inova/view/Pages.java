package lk.inova.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Pages {

	@RequestMapping("/login")
	public String login() {
		return "login.jsf";
	}
	@RequestMapping("/pages/index")
	public String index() {
		return "index.jsf";
	}
}
