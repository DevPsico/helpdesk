package com.ericson.helpdesk.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ericson.helpdesk.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@javax.annotation.PostConstruct
	public void instanciaDB() {
		
		this.dbService.instanciaDb();
		
	}

}
