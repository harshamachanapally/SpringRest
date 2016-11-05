package com.example.Layerdao;

import com.example.pojo.Account;


public interface AccountDao {

	public Account findByUserName(String userName);
}
