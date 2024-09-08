package com.example.demo.Repository;

import com.example.demo.Entity.ItemType;
import com.example.demo.Entity.ItemVariantDimension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemTypeRepository extends JpaRepository<ItemType, Integer> {
    Optional<ItemType> findBySid(Integer integer);

    Optional<ItemType> findBySidAndNameEn(Integer integer, String string);
}
