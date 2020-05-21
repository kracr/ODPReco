package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.springboot.controller.HelloController;
 
@SpringBootApplication

public class MainApp extends SpringBootServletInitializer{

	public static void main(String[] args) {
		
		
		SpringApplication.run(MainApp.class,args);
   /*This is to be run and it calls the index.jsp page on the web followed by the
    * execution of java classes whose sequence is maintained by the controller class
    * named-"HelloController.java".
    * 
    */
                    }
	/* Overloading the configure method of SpringBootServletInitializer. 
	 * It tells spring to build the sources from our Main class
	 */
	
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        return builder.sources(MainApp.class);
	    }
}
