package com.example.Layerservice;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.pojo.Account;


public interface AccountService {

	public UserDetails findByUserName(String userName);
}
