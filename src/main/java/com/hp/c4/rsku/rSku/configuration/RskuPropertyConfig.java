package com.hp.c4.rsku.rSku.configuration;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration

// ITG
@PropertySource(value = { "classpath:application.properties", "file:/c4/apps/epam/property/epam_config.properties",
		"file:/opt/sasuapps/c4/domain/c4/applications/rSku/c4RskuConfig.properties" })

////Local 
//@PropertySource(value = { "classpath:application.properties",
//"file:D:\\Pervez\\C4\\EPAM\\API Samples\\c4RskuConfig.properties",
//"file:D:\\Pervez\\C4\\EPAM\\API Samples\\epam_config.properties"})

public class RskuPropertyConfig {

	private static final Logger mLogger = LogManager.getLogger(RskuPropertyConfig.class);

	@Bean
	public PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		mLogger.info("Properties are loaded");
		return new PropertySourcesPlaceholderConfigurer();
	}
}
