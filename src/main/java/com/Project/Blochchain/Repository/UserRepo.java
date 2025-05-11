package com.Project.Blochchain.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Project.Blochchain.Entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
	
	Optional<User> findBymarsID(String mars_id);
	
	Optional<User> findByContact(String Contact);
	
	User findByUsername(String username);

}
