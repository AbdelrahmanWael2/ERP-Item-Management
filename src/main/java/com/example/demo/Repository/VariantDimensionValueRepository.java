package com.example.demo.Repository;

import com.example.demo.Entity.VariantDimensionValue;
import com.example.demo.Entity.VariantDimensionValuePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantDimensionValueRepository extends JpaRepository<VariantDimensionValue, VariantDimensionValuePK> {
}