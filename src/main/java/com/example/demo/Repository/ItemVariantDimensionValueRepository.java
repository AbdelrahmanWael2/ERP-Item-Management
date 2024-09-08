package com.example.demo.Repository;

import com.example.demo.Entity.ItemVariantDimension;
import com.example.demo.Entity.ItemVariantDimensionValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemVariantDimensionValueRepository extends JpaRepository<ItemVariantDimensionValue, Integer> {
    @Query("SELECT ivdv FROM ItemVariantDimensionValue ivdv WHERE ivdv.item.itemSid = :itemSid")
    List<ItemVariantDimensionValue> findByItemSid(@Param("itemSid") Integer itemSid);

    @Modifying
    @Query("DELETE FROM ItemVariantDimensionValue i WHERE i.itemClassId = :itemClassId")
    void deleteAllByItemClassId(@Param("itemClassId") Integer itemClassId);
}
