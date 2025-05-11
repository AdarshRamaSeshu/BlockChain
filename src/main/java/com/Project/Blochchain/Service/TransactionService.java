package com.Project.Blochchain.Service;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.Project.Blochchain.DTO.TransactionRequest;
import com.Project.Blochchain.Entity.Transaction;
import com.Project.Blochchain.Entity.Wallet;
import com.Project.Blochchain.Repository.TransactionRepo;
import com.Project.Blochchain.Utils.TransactionUtil;

import jakarta.transaction.Transactional;

@Service
public class TransactionService  {
	
	private final WalletService wallet_service_obj;// Wallet class object
	
	private final TransactionRepo transaction_repo_obj; // object for transaction repository
	
	TransactionService(WalletService Wallet_Service_obj,TransactionRepo Transaction_Repo_obj){
		
		this.wallet_service_obj=Wallet_Service_obj;
		this.transaction_repo_obj=Transaction_Repo_obj;
	}
	
	// Initiated transaction
	@Transactional
	public String initialize_Transaction(TransactionRequest txn_request) {

		Wallet senderWallet=wallet_service_obj.findByMarsId(txn_request.getSenderMarsId()); // get wallet of sender
		Wallet receiverWallet=wallet_service_obj.findByMarsId(txn_request.getReceiverMarsId()); // get wallet of receiver
		
		if (senderWallet == null || receiverWallet == null) {
            throw new IllegalArgumentException("Sender or Receiver not found"); // If any wallet is null throw exception
        }
		
		if(senderWallet.getBalance()<txn_request.getAmount()) {
			throw new IllegalArgumentException("Insufficient balance"); // If balnce of sender is not enough
		}
		
		Transaction transaction_obj=new Transaction(); // Object of new created transaction
		transaction_obj.setTransactonId(TransactionUtil.generateTransactionId()); // set transaction ID of new transaction
		transaction_obj.setAmount(txn_request.getAmount()); // set amount in the transaction
		transaction_obj.setReceiverMarsId(receiverWallet.getMars_id()); // set receiver mars id
		transaction_obj.setSenderMarsId(senderWallet.getMars_id()); // set sender mars id
		transaction_obj.setSenderWalletId(senderWallet.getWallet_id());// set sender wallet id
		transaction_obj.setReceiverWalletId(receiverWallet.getWallet_id());// set receiver wallet id
		transaction_obj.setTimeStamp(LocalDateTime.now());//set transaction time stamp
		
		transaction_repo_obj.save(transaction_obj);// save to transaction to transaction table
		
		senderWallet.setBalance(senderWallet.getBalance()-txn_request.getAmount());
		receiverWallet.setBalance(receiverWallet.getBalance()+txn_request.getAmount());
		
		
	    return "Transaction Successful!";
	    
	}

}
