package com.Project.Blochchain.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Project.Blochchain.DTO.TransactionRequest;
import com.Project.Blochchain.Service.TransactionService;

@RestController
@RequestMapping("/txn")
public class TransactionController {
	
	
	private TransactionService txnservice;
	
	public TransactionController(TransactionService TxnService) {
		this.txnservice=TxnService;
	}
	
	@PostMapping
	public ResponseEntity<String> doTransaction(@RequestBody TransactionRequest request){
		String result= txnservice.initialize_Transaction(request);
		return ResponseEntity.ok(result);
	}
	
}
