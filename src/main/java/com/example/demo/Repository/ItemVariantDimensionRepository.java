package com.example.demo.Repository;

import com.example.demo.Entity.ItemType;
import com.example.demo.Entity.ItemVariantDimension;
import com.example.demo.Entity.ItemVariantDimensionPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemVariantDimensionRepository extends JpaRepository<ItemVariantDimension, ItemVariantDimensionPK> {
    Optional<ItemVariantDimension> findById_dimensionId(Integer integer);
    @Query("SELECT i FROM ItemVariantDimension i WHERE i.id.itemSid = :itemSid")
    List<ItemVariantDimension> findByItemSid(@Param("itemSid") Integer itemSid);

    @Modifying
    @Query("DELETE FROM ItemVariantDimension i WHERE i.itemClassId = :itemClassId")
    void deleteAllByItemClassId(@Param("itemClassId") Integer itemClassId);
}
