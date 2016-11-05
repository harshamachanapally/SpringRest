package com.example.Layerrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pojo.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	public Account findByuserNameIgnoreCase(String userName);
}
