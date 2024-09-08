package com.example.demo.Repository;

import com.example.demo.Entity.PhItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhItemRepository extends JpaRepository<PhItem, Integer> {
    List<PhItem> findByItemClassId(Integer itemClassId);
}