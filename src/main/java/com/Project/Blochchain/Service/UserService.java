package com.Project.Blochchain.Service;

import java.util.List;

//import java.util.ArrayList;
//import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Project.Blochchain.Configuration.CustomUserDetails;
import com.Project.Blochchain.Entity.User;
import com.Project.Blochchain.Repository.UserRepo;

import jakarta.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {
	
	
	private User userObject;
	
	
	@Autowired
	private  UserRepo userrepo;
	
	@Autowired
	private WalletService walletservice;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//Method to add user as ADMIN
	@PreAuthorize("hasRole('ADMIN')")
	public void addAdmin(User adminrequest) {
		this.userObject=adminrequest;
		Optional<User> existinguser=userrepo.findByContact(userObject.getContact()); // Check if there is any existing user with contact.
		if(existinguser.isPresent()) { // if existing user is not null
			throw new RuntimeException("Admin Already Exists!!!"); // throw runtime exception
		}
		
		String marsid="NA"; //admin's won't have mars id
		userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));// Hashing and encoding the password
		userObject.setMARS_ID(marsid); // set as NA
		userrepo.save(userObject); // Saving adminrequest to user table
		
		System.out.println("Peer Added as Admin Sucessfully");
		
	}
	
	
	@Transactional
	public User addUser(User userrequest) {
		
		Optional<User> existinguser=userrepo.findByContact(userrequest.getContact()); // check if user already exist or not
		
		if(existinguser.isPresent()) {
			throw new RuntimeException("User Already Exists"); // If exists throw exception
		}
		userrequest.setPassword(passwordEncoder.encode(userrequest.getPassword())); // // Hashing and encoding the password
		userrequest.setMARS_ID(userrequest.getContact());// Manually setting the mars id
		userrepo.save(userrequest);//Saving the user request object to database
		walletservice.createWallet(userrequest);
		return userrequest;
	}


	public Optional<User> findByMarsId(String senderMarsId) {
		Optional<User> sender=userrepo.findBymarsID(senderMarsId);
		if(sender.isEmpty()) {
			System.out.println("Sender not found");
			return null;
		}
		return sender;
	}


	

	@Override
	public UserDetails loadUserByUsername(String Username) throws UsernameNotFoundException {
		try {
			User user = userrepo.findByUsername(Username);
			if (user == null) {
		        throw new UsernameNotFoundException("User not found with username: " + Username);
		    }
			String role=user.getRole().name();
			return new CustomUserDetails(user.getUsername(),user.getPassword(),user.getMARS_ID(),List.of(new SimpleGrantedAuthority("Role_"+role))) ;
		}
		catch(UsernameNotFoundException e) {
			System.out.println("UsernameNotFoundException"+" "+e);
			return null;
		}
		
		}


}
