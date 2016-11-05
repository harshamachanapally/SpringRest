package com.example.Layerservice;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Layerdao.AccountDao;
import com.example.pojo.Account;
import com.example.pojo.Role;

@Service
public class AccountServiceImp implements AccountService {
	
	@Autowired
	AccountDao accountDao;
	
	public UserDetails findByUserName(String userName) throws UsernameNotFoundException {
		
		Account account = accountDao.findByUserName(userName);
		
		if(account == null)
			return null;
		
		Collection<GrantedAuthority> grantedAuthority = new ArrayList<GrantedAuthority>();
		
		for(Role role : account.getRoles()){
			grantedAuthority.add(new SimpleGrantedAuthority(role.getCode()));
		}
		
		User user = new User(account.getUserName(),account.getPasssword(),account.isEnabled(),!account.isExpired(),
							!account.isCredentailsExpired(),!account.isLocked(),grantedAuthority); 
		
		return user;
		
	}

}
