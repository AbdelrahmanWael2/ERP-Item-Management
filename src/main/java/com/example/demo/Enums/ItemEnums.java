package com.example.demo.Enums;

public class ItemEnums {

    public enum TypeOfCoding {
        SEQUENTIAL(1),
        MANUAL(2);

        private final int value;

        TypeOfCoding(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum ValuationMethod {
        WEIGHTED_AVERAGE(1),
        FIFO(2),
        STANDARD(3);

        private final int value;

        ValuationMethod(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum ItemType {
        PRODUCT(1),
        SCRAP(2);

        private final int value;

        ItemType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum BrandsModelsOptions{
        None(0),
        Brands(1),
        Brands_And_Models(2);

        private final int value;

        BrandsModelsOptions(int value){this.value = value;}

        public int getValue(){return value;}
    }

    public enum ManagementMethod {
        BATCH(1),
        SERIAL(2),
        NONE(3);

        private final int value;

        ManagementMethod(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
