package com.example.demo.Repository;

import com.example.demo.Entity.VariantDimensionValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DimensionValueDto extends JpaRepository<VariantDimensionValue, Integer> {
}
