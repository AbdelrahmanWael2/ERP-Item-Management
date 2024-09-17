package com.example.demo.Enums;


import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;

public class ItemTypeEnums {

    @Getter
    public enum ItemTypes {
        VARIANT(1), // 1
        ITEM(0), // 0
        TEMPLATE(2); //2

        private final Integer value;

        ItemTypes(Integer value) {
            this.value = value;
        }

    }
}
