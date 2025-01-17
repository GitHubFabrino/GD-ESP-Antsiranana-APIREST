package com.appli.springjwt;

import com.appli.springjwt.service.FilesStorageService;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.Resource;

@SpringBootApplication
@EnableAsync
public class SpringBootSecurityJwtApplication  extends SpringBootServletInitializer implements CommandLineRunner {
	@Resource
	FilesStorageService storageService;

	public static void main(String[] args) {
    SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
	System.out.println("===============================================");
	System.out.println("==========  Application version 10 version modifier bd v3   =============");
	System.out.println("===============================================");
	}

	@Bean
	public Hibernate5Module datatypeHibernateModule(){
		return new Hibernate5Module();
	}

	@Override
	public void run(String... arg) throws Exception {
//    storageService.deleteAll();
		storageService.init();
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootSecurityJwtApplication.class);
	}

}
