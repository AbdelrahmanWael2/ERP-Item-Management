package com.example.demo.DTO;

import com.example.demo.Entity.PhItem;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhItemClassDto {


    // master  Data
    private Integer itemSid;
    private String nameAr;
    private String nameEn;
    private Integer itemClassId;
    private Integer itemGroupId;
    private Boolean sellable;
    private Boolean purchasable;
    private Boolean stockable;
    private Byte hasVariants;
    private Byte hasVersions;
    private String itemCode;
    private String barCode;
    private String barCodeEn;

    private Integer packingUnitId;

    private Integer taxGroupId;

    private Integer customGroupId ;

    private Integer type;

    private String comment;

    private String commentEn;

    private byte canBeAddition;

    private Byte activeFlag;


//    // stock
//    private double avaliableQuantity;
//
//    private double commitedQuantity;
//
//    private double minStockLevel;
//    private double orderdQuantity;
//    private double qoh;
//    private double reorderPoint;
//
//    private double weightedAverageCost;


//    private List<PhItem> variants ;


}



