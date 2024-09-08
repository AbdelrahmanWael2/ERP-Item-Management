package com.example.demo.Enums;


public class ItemTypeEnums {

    public enum ItemTypes {
        VARIANT("variant"),
        ITEM("item"),
        TEMPLATE("template");

        private final String value;

        ItemTypes(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
