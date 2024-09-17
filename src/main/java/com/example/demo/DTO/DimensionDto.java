package com.example.demo.DTO;

import lombok.Data;

import java.util.List;

@Data
public class DimensionDto {
    private Integer dimensionId;
    private String dimensionNameEn;
    private String dimensionNameAr;
    private List<DimensionValueDto> dimensionValues;
}
