package com.example.demo.DTO;
import com.example.demo.Enums.ItemEnums;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;


@Getter
@Setter
@Validated
public class ItemClassDTO {

    private Integer itemClassId;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name can't be blank")
    private String name;

    @Enumerated(EnumType.STRING)
    private ItemEnums.TypeOfCoding typeOfCode;

    private String comment;
    private Boolean activeFlag;
    private Boolean expiry;
    private Boolean sellable;
    private Boolean purchasable;
    private Boolean crmItemClass;
    private Boolean stockable;
    private Boolean hasBarcode;
    private ItemEnums.ManagementMethod managementMethod;
    private ItemEnums.ValuationMethod valuationMethod;
    private ItemEnums.BrandsModelsOptions brandsModelsOptions;
    private Boolean quantityChangable;
    private Integer periodicMaintenanceServiceItemSid;
    private Integer periodicMaintenanceServiceMonths;
    private Boolean hasOneUnit;
    private Boolean isMergable;
    private ItemEnums.ItemType itemType;
    private Boolean customerService;
}