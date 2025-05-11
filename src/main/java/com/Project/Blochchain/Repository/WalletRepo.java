package com.Project.Blochchain.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Project.Blochchain.Entity.Wallet;

public interface WalletRepo extends JpaRepository<Wallet , String> {
	
	//search wallet table using wallet id
	Optional<Wallet> findByWalletId(String walletId);
	
	//search wallet table using mars_id
	Wallet findByMarsId(String marsId);
	
	//search wallet table using user_id
	Optional<Wallet> findByUserId(Long user_id);
	
	
	

}
