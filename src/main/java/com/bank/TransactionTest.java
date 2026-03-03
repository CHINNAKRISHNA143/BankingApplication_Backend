package com.bank;

import java.math.BigDecimal;

import com.bank.exceptions.AccountNotFoundException;
import com.bank.exceptions.InvalidAmountException;
import com.bank.model.Account;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import com.bank.service.AccountService;
import com.bank.service.TransactionService;

public class TransactionTest {

	public static void main(String[] args) {
		
		AccountRepository accRepo = new AccountRepository();
		TransactionRepository trxRepo = new TransactionRepository();
		
		AccountService accountService = new AccountService(accRepo);
		TransactionService trxService = new TransactionService(accountService,trxRepo);
		
		try {
			
			Account account1 = accountService.createAccount("Krish", "krish@gmail.com", new BigDecimal("5000"));
			trxService.withdraw(account1.getAccountNumber(), new BigDecimal("500"));
			
		} catch (InvalidAmountException | AccountNotFoundException e) {
			 
			e.printStackTrace();
		}
		
		
	}

}
