package com.Project.Blochchain.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="transactions")
public class Transaction {
	
	@Id
	private String transactonId;
	
	private String senderWalletId;
	
	private String receiverWalletId;
	
	private double amount;
	
	private String senderMarsId;
	
	private String receiverMarsId;
	
	private LocalDateTime timeStamp;

	public String getTransactonId() {
		return transactonId;
	}

	public void setTransactonId(String transactonId) {
		this.transactonId = transactonId;
	}

	public String getSenderWalletId() {
		return senderWalletId;
	}

	public void setSenderWalletId(String senderWalletId) {
		this.senderWalletId = senderWalletId;
	}

	public String getReceiverWalletId() {
		return receiverWalletId;
	}

	public void setReceiverWalletId(String receiverWalletId) {
		this.receiverWalletId = receiverWalletId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getSenderMarsId() {
		return senderMarsId;
	}

	public void setSenderMarsId(String senderMarsId) {
		this.senderMarsId = senderMarsId;
	}

	public String getReceiverMarsId() {
		return receiverMarsId;
	}

	public void setReceiverMarsId(String receiverMarsId) {
		this.receiverMarsId = receiverMarsId;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	
}
