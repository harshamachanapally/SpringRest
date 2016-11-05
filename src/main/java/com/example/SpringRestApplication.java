package com.example;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.SecurityConfiguration;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
public class SpringRestApplication {
	final static Logger logger = Logger.getLogger(SpringRestApplication.class);
	public static void main(String[] args) {
		//logger.debug("Application started -- By Harsha");
		SpringApplication.run(SpringRestApplication.class, args);
	}
	
	@Bean
	public CacheManager cacheManager(){
		//ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager("customers");
		GuavaCacheManager cacheManager = new GuavaCacheManager();
		Set<String> cacheNames = new HashSet<String>(); 
		cacheNames.add("orders");
		cacheNames.add("customers");
		//cacheManager.setCacheBuilder(cacheBuilder);
		cacheManager.setCacheNames(cacheNames);
		return cacheManager;
	}
}
