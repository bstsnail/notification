package com.bstsnail;

import com.bstsnail.co.EventCO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NotificationApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(NotificationApplication.class, args);
		context.getBean(EventCO.class).scheduleEvent();
	}
}
