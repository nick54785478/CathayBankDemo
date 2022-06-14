package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Bitcoin;

@Repository
public interface BitCoinRepository extends JpaRepository<Bitcoin, String> {

	Bitcoin findByCode(String code);
}
