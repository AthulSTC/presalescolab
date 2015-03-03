package com.presalescolab;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class RootController {

	@RequestMapping(method = RequestMethod.GET)
	public void printWelcome(HttpServletResponse response) {

        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location", "index.html");
	}

}