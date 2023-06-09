package com.example.giftapi.repository;

import com.example.giftapi.model.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftRepository extends JpaRepository<Gift, Integer> {

}
