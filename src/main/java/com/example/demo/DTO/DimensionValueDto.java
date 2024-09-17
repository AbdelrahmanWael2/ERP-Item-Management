package com.example.demo.DTO;


import lombok.Data;

@Data
public class DimensionValueDto {
    private Integer valueSer;
    private String valueNameEn;
    private String valueNameAr;
    private double amountAdded;
    private byte defaultValue;
}
