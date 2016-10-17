package com.onemore;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OneMoreBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneMoreBackendApplication.class, args);
	}
	
//	@Bean
//	public CommandLineRunner demo(UserRepository repository) {
//		return (args) -> {
//			repository.save(new User("admin"));
//			repository.save(new User("admin2"));
//		};
//	}
	
	@Bean
	public ServletRegistrationBean h2servletRegistration() {
	    ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
	    registration.addUrlMappings("/console/*");
	    return registration;
	}
}
