package com.walmart.reminderservice.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration class. Beans may be defined in this class.
 * @author J
 *
 */
@Configuration
@EntityScan("com.walmart.reminderservice.db.entity")
@EnableJpaRepositories("com.walmart.reminderservice.db.repository")
@EnableJpaAuditing
@EnableTransactionManagement
public class ReminderConfig {

	
	
}
