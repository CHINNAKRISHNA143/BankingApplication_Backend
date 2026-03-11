package com.bank;

import java.math.BigDecimal;
import java.util.Scanner;

import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import com.bank.service.AccountService;
import com.bank.service.AlertService;
import com.bank.service.TransactionService;

public class BankingFinalTest {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		AccountRepository accRepo = new AccountRepository();
		AccountService accService = new AccountService(accRepo);
		TransactionRepository trxRepo = new TransactionRepository();
		AlertService alertService = new AlertService(new BigDecimal("1000"));
		TransactionService trxService = new TransactionService(accService, trxRepo, alertService);
		
		System.out.println("===========================================================================");
		System.out.println("WELCOME TO OUR BANKING APPLICATION");
		System.out.println("===========================================================================");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
