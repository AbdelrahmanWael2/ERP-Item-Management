package com.example.demo.Enums;


import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;

public class ItemTypeEnums {

    @Getter
    public enum ItemTypes {
        VARIANT(2),
        ITEM(1),
        TEMPLATE(3);

        private final Integer value;

        ItemTypes(Integer value) {
            this.value = value;
        }

    }
}
