package com.bank.service;

import java.math.BigDecimal;

import com.bank.exceptions.AccountNotFoundException;
import com.bank.model.Account;

public class TransactionService {
	
	private AccountService accountService;
	
	public TransactionService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	public void deposite(String accNo, BigDecimal amount) throws AccountNotFoundException{
		Account account = accountService.getAccount(accNo);
	}

}
