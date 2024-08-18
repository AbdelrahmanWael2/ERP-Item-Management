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

    @Enumerated(EnumType.STRING) // Matching enum types
    private ItemEnums.TypeOfCoding typeOfCode;

    private String comment;
    private Boolean activeFlag; // Changed to match entity field name
    private Boolean expiry;
    private Boolean sellable;
    private Boolean purchasable;
    private Boolean crmItemClass;
    private Boolean stockable;
    private Boolean hasBarcode;
    private ItemEnums.ManagementMethod managementMethod;
    private ItemEnums.ValuationMethod valuationMethod;
    private ItemEnums.BrandsModelsOptions brandsModelsOptions; // Updated type
    private Boolean quantityChangable;
    private Integer periodicMaintenanceServiceItemSid;
    private Integer periodicMaintenanceServiceMonths;
    private Boolean hasOneUnit;
    private Boolean isMergable;
    private ItemEnums.ItemType itemType; // Changed field name to itemType

    private Boolean customerService; // If needed, otherwise remove or adjust as required
}