package com.example.giftapi.repository;

import com.example.giftapi.model.Kid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KidRepository extends JpaRepository<Kid, Integer> {

}
