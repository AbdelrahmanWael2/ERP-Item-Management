package com.example.demo.DTO;

import lombok.Data;

@Data
public class VariantDimensionValueDto {
    private int dimensionId;
    private int valueSer;
    private String valueCode;
    private String valueNameEn;
    private String valueNameAr;
    private byte activeFlag;
    private double amountAdded;
    private byte defaultValue;
}