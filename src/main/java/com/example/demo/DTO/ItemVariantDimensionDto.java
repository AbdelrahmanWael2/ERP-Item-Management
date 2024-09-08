package com.example.demo.DTO;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ItemVariantDimensionDto {
    private int dimensionId;
    private byte activeFlag;
    private String createdBy;
    private Date creationDate;
    private int dimensionSer;
    private int itemClassId;
    private String itemCode;
    private Date modificationDate;
    private String modifiedBy;
    private byte selectDefaultValue;
    private byte showFullPrice;
}