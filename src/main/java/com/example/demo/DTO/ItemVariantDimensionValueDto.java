package com.example.demo.DTO;

import com.example.demo.Entity.ItemVariantDimensionValue;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ItemVariantDimensionValueDto {
    private int dimensionId;
    private byte activeFlag;
    private double amountAdded;
    private String createdBy;
    private Date creationDate;
    private byte defaultValue;
    private int itemClassId;
    private String itemCode;
    private Date modificationDate;
    private String modifiedBy;
    private String valueCode;
    private String valueNameAr;
    private String valueNameEn;

}
