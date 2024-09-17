package com.example.demo.Repository;

import com.example.demo.Entity.PhItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PhItemRepository extends JpaRepository<PhItem, Integer> {
    List<PhItem> findByItemClassId(Integer itemClassId);
    @Modifying
    @Query("UPDATE PhItem p SET p.itemType = 0 WHERE p.itemSid = :sid")
    int updateItemTypeToZero(@Param("sid") Integer sid);

}