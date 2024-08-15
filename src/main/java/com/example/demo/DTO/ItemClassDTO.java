package com.example.demo.DTO;
import com.example.demo.Enums.ItemEnums;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;


@Getter
@Setter
@Validated
public class ItemClassDTO {

    private Integer ItemClassId;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name cant be blank")
    private String name;

    @NotNull(message = "Parent Item Class ID is required")
    private Integer parentItemClassId;

    private String tableName;
    private Integer attributeGroupId;
    @Getter
    private ItemEnums.TypeOfCoding typeOfCode;
    private String comment;
    private Boolean allowNegativeBalance;
    private Boolean appearInOperation;
    private Boolean expiry;
    private Boolean sellable;
    private Boolean purchasable;
    private Boolean crmItemClass;
    private Boolean stockable;
    private String nameEn;
    private Boolean sellingPriceFromBatch;
    private Double profitMargin;
    private String tableNameEn;
    private String internalClassCode;
    private String internalClassCodeEn;
    private String commentEn;

    private Integer autoGenerateCode;
    private Boolean isMergable;
    private Boolean hasBarcode;
    private Integer itemClassTypeId;
    private Boolean isDefault;
    private ItemEnums.ManagementMethod managementMethod;
    private Integer serialCount;
    private Boolean hasVersion;
    private Boolean useStandardColors;
    private ItemEnums.ValuationMethod valuationMethod;
    private Integer brandsModelsOptions;

    private Integer companyId;

    private Boolean quantityChangable;
    private Boolean hasCrossNetWeight;
    private Integer itemClassPackingUnitId;
    private ItemEnums.ItemType itemClassItemsTypeId;
    private String purchasingCostGlAccountCode;
    private String salesCostGlAccountCode;
    private String salesRevenueGlAccountCode;
    private String losesGlAccountCode;
    private Boolean serialIsRequired;
    private Integer periodicMaintenanceServiceItemSid;
    private Integer periodicMaintenanceServiceMonths;
    private Boolean hasOneUnit;
    private Boolean additions;

}
