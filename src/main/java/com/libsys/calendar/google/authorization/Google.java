package com.libsys.calendar.google.authorization;

import org.apache.catalina.User;

public class Google extends ApiBinding{

	public Google(String accessToken) {
		super(accessToken);
		// TODO Auto-generated constructor stub
	}
	
	public User getCalendarEvent() {
		return restTemplate.getForObject("someurl", User.class);
	}

}
