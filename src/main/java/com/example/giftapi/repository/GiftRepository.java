package com.example.giftapi.repository;

import com.example.giftapi.model.Gift;
import org.springframework.data.jpa.repository.JpaRepository;


import com.example.giftapi.model.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GiftRepository extends JpaRepository<Gift, Integer> {
    List<Gift> findByKidId(int kidId);

    Optional<Gift> findByIdAndKidId(int id, int kidId);

    void deleteByIdAndKidId(int id, int kidId);
}

