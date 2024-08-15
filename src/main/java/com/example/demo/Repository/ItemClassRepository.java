package com.example.demo.Repository;

import com.example.demo.Entity.ItemClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemClassRepository extends JpaRepository<ItemClass, Integer> {
    List<ItemClass> findByActiveFlagTrue();
    Optional<ItemClass> findByName(String name);

    void deleteByName(String name);
}
