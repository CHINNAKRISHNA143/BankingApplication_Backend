package com.bank.service;

import java.math.BigDecimal;

import com.bank.exceptions.AccountNotFoundException;
import com.bank.exceptions.InsufficientBalanceException;
import com.bank.exceptions.InvalidAmountException;
import com.bank.model.Account;
import com.bank.repository.TransactionRepository;

public class TransactionService {
	
	private final AccountService accountService;
	private final TransactionRepository txRepo;
	
	public TransactionService(AccountService accountService,TransactionRepository txRepo) {
		this.accountService = accountService;
		this.txRepo = txRepo;
	}
	
	public void deposite(String accNo, BigDecimal amount) throws AccountNotFoundException, 
	InvalidAmountException
	{
		
		if(amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new InvalidAmountException("Amount must be positive..!");
		}
		
		
		Account account = accountService.getAccount(accNo);
		account.credit(amount);
		txRepo.logTransaction("DEPOSITE", accNo, amount.doubleValue(), null);
		System.out.println("Deposited : "+amount+" to "+accNo);
		
	}
	
	public void withdraw(String accNo, BigDecimal amount) throws AccountNotFoundException, 
	InvalidAmountException, InsufficientBalanceException
	{
		
		if(amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new InvalidAmountException("Amount must be positive..!");
		}
		
		
		Account account = accountService.getAccount(accNo);
		if(account.getOpeningBalance().compareTo(amount) < 0) {
			throw new InsufficientBalanceException("Insufficient Balance..!");
		}
		
		account.debit(amount);
		txRepo.logTransaction("WITHDRAW", accNo, amount.doubleValue(), null);
		System.out.println("Withdraw : "+amount+" to "+accNo);
		
	}
	
	public void transfer(String fromAcc, String toAcc,BigDecimal amount) throws InvalidAmountException, AccountNotFoundException, InsufficientBalanceException{
		
		if(amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new InvalidAmountException("Amount must be positive..!");
		}
		
		Account sender = accountService.getAccount(fromAcc);
		Account receiver = accountService.getAccount(toAcc);
		
		if(sender.getOpeningBalance().compareTo(amount) < 0) {
			throw new InsufficientBalanceException("Insufficient Balance..!");
		}
		
		sender.debit(amount);
		receiver.credit(amount);
		
		txRepo.logTransaction("TRANSFER", fromAcc, amount.doubleValue(), toAcc);
		System.out.println(amount +" Transfer Successfull from : "+fromAcc+" to "+toAcc);
	}
	
	

}





