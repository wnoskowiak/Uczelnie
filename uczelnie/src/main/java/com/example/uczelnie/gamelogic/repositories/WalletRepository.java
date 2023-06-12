package com.example.uczelnie.gamelogic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.uczelnie.gamelogic.Wallet;


@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    
}