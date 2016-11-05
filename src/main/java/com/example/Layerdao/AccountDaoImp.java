package com.example.Layerdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.Layerrepository.AccountRepository;
import com.example.pojo.Account;

@Repository
public class AccountDaoImp implements AccountDao {
	
	@Autowired
	AccountRepository accountRepository;
	
	public Account findByUserName(String userName) {
		
		Account account = accountRepository.findByuserNameIgnoreCase(userName);
		return account;
	}

}
