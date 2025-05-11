package com.Project.Blochchain.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Wallets")
public class Wallet {
	
	
	@Id
	@Column(name = "Wallet_Id", nullable = false, unique = true)
	private String walletId; // primary key
	@Column(name = "Mars_Id", nullable = false, unique = true)
	private String marsId;
	@Column(length=2048)
	private String publickey;
	@Column(length=2048)
	private String privatekey;
	private double Balance;
	
	@OneToOne
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private User user;
	
	public void setUser(User requstedUser) {
		this.user=requstedUser;
	}
	

	public Wallet() {
	}
	
	public void setWallet_id(String wallet_id) {
		walletId = wallet_id;
	}

	public String getWallet_id() {
		return walletId;
	}

	public String getPublickey() {
		return publickey;
	}
	public void setPublickey(String publickey) {
		this.publickey = publickey;
	}
	public String getPrivatekey() {
		return privatekey;
	}
	public void setPrivatekey(String privatekey) {
		this.privatekey = privatekey;
	}


	public double getBalance() {
		return Balance;
	}


	public void setBalance(double balance) {
		Balance = balance;
	}

	public String getMars_id() {
		return marsId;
	}

	public void setMars_id(String mars_id) {
		this.marsId = mars_id;
	}



	
}
