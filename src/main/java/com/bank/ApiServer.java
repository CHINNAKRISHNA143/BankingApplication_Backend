package com.bank;

import static spark.Spark.*;
import java.math.BigDecimal;

 
import com.bank.model.Account;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import com.bank.service.AccountService;
import com.bank.service.AlertService;
import com.bank.service.TransactionService;
import com.google.gson.Gson;

public class ApiServer {
	

	public static void main(String[] args) {
		
		port(8080);
		enableCORS();
		Gson gson = new Gson();
		
		AccountRepository accRepo = new AccountRepository();
		AccountService accService = new AccountService(accRepo);
		TransactionRepository trxRepo = new TransactionRepository();
		AlertService alertService = new AlertService(new BigDecimal("1000"));
		TransactionService trxService = new TransactionService(accService, trxRepo, alertService);
		
		post("/accounts/create",(req,res) -> {
			res.type("application/json");
			System.out.println("/accounts/create API Called.." );
			AccountRequest data = gson.fromJson(req.body(),AccountRequest.class);
			Account acc = accService.createAccount(data.name, data.email, data.balance);
			return gson.toJson(acc);
			
		});
		
		 post("/transactions/deposite", (req, res) -> {
	            System.out.println("/transactions/deposite api is called");

	            TxRequest data = gson.fromJson(req.body(), TxRequest.class);
	            trxService.deposite(data.accNo, data.amount);

	            return "Deposite successfully..!";
	        });
		
		post("/tranctions/withdraw",(req,res) ->{
			System.out.println("/tranctions/withdraw API Called.." );
			TxRequest data = gson.fromJson(req.body(), TxRequest.class);
			trxService.withdraw(data.accNo, data.amount);
			return "Withdraw Successfully..!";
		});
		
		post("/transactions/transfer",(req,res) ->{
			System.out.println("/tranctions/transfer API Called.." );
			TransferRequest data = gson.fromJson(req.body(), TransferRequest.class);
			trxService.transfer(data.fromAcc, data.toAcc,data.amount);
			return "Transfer Transaction Successfull..!";
		});
		
		get("/accounts/all",(req,res) -> {
			System.out.println("/accounts/all API is called");
			res.type("application/json");
			
			return gson.toJson(accService.listAllAccounts());
		});
		
		get("/accounts/:accNo",(req,res) -> {
			String accNo = req.params("accNo");
			Account account = accService.getAccount(accNo);
			return gson.toJson(account);
		});
		
	}
	
	public static void enableCORS() {
		options("/*",(request,response) -> {
			String reqheaders = request.headers("Access-Control-Request-Headers");
			if(reqheaders != null) {
				response.header("Access-Control-Allow-Headers", reqheaders);
			}
			
			String reqMethod = request.headers("Access-Control-Request-Method");
			if(reqMethod != null) {
				response.header("Access-Control-Allow-Methods", reqMethod);
			}
			
			return "OK";
			
		});
		
		before((req,res) -> {
			res.header("Access-Control-Allow-Origin","*");
			res.header("Access-Control-Allow-Methods","GET,POST,PUT,DELETE,OPTIONS");
			res.header("Access-Control-Allow-Headers","Content-Type,Authorization");
		});
		
	}
	
	static class AccountRequest{
		String name;
		String email;
		BigDecimal balance;
	}
	
	static class TxRequest{
		String accNo;
		BigDecimal amount;
	}
	
	static class TransferRequest{
		String fromAcc;
		String toAcc;
		BigDecimal amount;
		
	}

}



















