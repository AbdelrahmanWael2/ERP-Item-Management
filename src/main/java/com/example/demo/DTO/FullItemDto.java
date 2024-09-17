package com.example.demo.DTO;

import com.example.demo.Enums.ItemTypeEnums;
import lombok.Data;

import java.util.List;


@Data
public class FullItemDto {
    private Integer itemSid;
    private String itemNameAr;
    private String itemNameEn;
    private String itemCode;
    private Integer itemClassId;
    private Byte hasVariants;
    private Integer itemType;
    private List<DimensionDto> dimensions;
}
