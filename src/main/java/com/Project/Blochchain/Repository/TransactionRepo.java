package com.Project.Blochchain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Project.Blochchain.Entity.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction,String> {

}
