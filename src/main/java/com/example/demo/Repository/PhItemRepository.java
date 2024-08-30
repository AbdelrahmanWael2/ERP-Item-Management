package com.example.demo.Repository;

import com.example.demo.Entity.PhItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhItemRepository extends JpaRepository<PhItem, Integer> {
}