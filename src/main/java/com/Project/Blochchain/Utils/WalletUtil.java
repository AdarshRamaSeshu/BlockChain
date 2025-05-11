package com.Project.Blochchain.Utils;


import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.UUID;

public class WalletUtil {
	


	public static KeyPair generateKeyPair() {
		try {
		KeyPairGenerator keyGen= KeyPairGenerator.getInstance("RSA"); 
		keyGen.initialize(2048);
		return keyGen.generateKeyPair();
		}
		catch(Exception e) {
			 throw new RuntimeException("KeyPair Generation Failed" , e );
		}
	}
	
		
		
	
	//Method to create wallet id's for users
	public static String generateWalletId() {
	
		return "Wallet_"+UUID.randomUUID().toString().substring(0,8);
	}




	public static String getPublicKeyAsString(PublicKey publickey) {
		return Base64.getEncoder().encodeToString(publickey.getEncoded());
	}




	public static String getPrivateKeyAsString(PrivateKey privatekey) {
		
		return Base64.getEncoder().encodeToString(privatekey.getEncoded());
	}

}
