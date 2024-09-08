package com.example.demo.DTO;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class VariantDimensionDto {
    private int dimensionId;
    private String dimensionNameEn;
    private String dimensionNameAr;
    private byte activeFlag;
    private byte standardDimension;
    private Integer displayType;
    private String createdBy;
    private Date creationDate;
    private String modifiedBy;
    private Date modificationDate;
}
