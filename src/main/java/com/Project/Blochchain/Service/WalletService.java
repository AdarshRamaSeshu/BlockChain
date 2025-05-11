package com.Project.Blochchain.Service;
import java.security.KeyPair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Project.Blochchain.Entity.User;
import com.Project.Blochchain.Entity.Wallet;
import com.Project.Blochchain.Repository.WalletRepo;
import com.Project.Blochchain.Utils.WalletUtil;


@Service
public class WalletService {
	
	@Autowired
	private WalletRepo walletrepo;
	
	public void createWallet(User user) {
		try {
			
			//Generate Key Pair
			KeyPair keypair= WalletUtil.generateKeyPair();
			System.out.println("Key Pair Generated for this User");
			
			//Getting Key pair in encoded format
			String public_Key=WalletUtil.getPublicKeyAsString(keypair.getPublic());
			String private_key=WalletUtil.getPrivateKeyAsString(keypair.getPrivate());
			
			//creation of wallet id 
			String walletId=WalletUtil.generateWalletId();			
			

	        Wallet wallet = new Wallet();
	        wallet.setWallet_id(walletId);
	        wallet.setBalance(100.0);
	        wallet.setPublickey(public_Key);
	        wallet.setPrivatekey(private_key);
	        wallet.setUser(user);
	        wallet.setMars_id(user.getMARS_ID());
	        
	        walletrepo.save(wallet);
	        
			System.out.println("Wallet Creation Sucessfull and 100 Mars coin to Wallet");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public Wallet findByMarsId(String marsId) {
		
		return walletrepo.findByMarsId(marsId) ;
		 
	}


}