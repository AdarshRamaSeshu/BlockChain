package com.Project.Blochchain.DTO;

public class TransactionRequest {
	
	private String senderMarsId;
	
	private String receiverMarsId;
	
	private double amount;

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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
