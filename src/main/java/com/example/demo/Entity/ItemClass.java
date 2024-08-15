package com.example.demo.Entity;

import com.example.demo.Enums.ItemEnums;
import jakarta.persistence.*;
import java.util.Date;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@Table(name = "ph_item_classes")
public class ItemClass {

    @Id
    @GeneratedValue(generator = "itemClassIdGenerator")
    @GenericGenerator(name = "itemClassIdGenerator", strategy = "com.example.demo.Utility.ItemClassIdGenerator")
    @Column(name = "ITEM_CLASS_ID")
    private Integer itemClassId;

    @Column(name = "PARENT_ITEM_CLASS_ID")
    private Integer parentItemClassId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TABLE_NAME")
    private String tableName;

    @Column(name = "ATTRIBUTE_GROUP_ID")
    private Integer attributeGroupId;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_OF_CODE")
    private ItemEnums.TypeOfCoding typeOfCode;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Column(name = "MODIFICATION_DATE")
    private Date modificationDate;

    @Column(name = "ACTIVE_FLAG")
    private Boolean activeFlag;

    @Column(name = "ALLOW_NEGATIVE_BALANCE")
    private Boolean allowNegativeBalance;

    @Column(name = "APPEAR_IN_OPERATION")
    private Boolean appearInOperation;

    @Column(name = "EXPIRY")
    private Boolean expiry;

    @Column(name = "SELLABLE")
    private Boolean sellable;

    @Column(name = "PURCHASABLE")
    private Boolean purchasable;

    @Column(name = "CRM_ITEM_CLASS")
    private Boolean crmItemClass;

    @Column(name = "STOCKABLE")
    private Boolean stockable;

    @Column(name = "NAME_EN")
    private String nameEn;

    @Column(name = "SELLING_PRICE_FROM_BATCH")
    private Boolean sellingPriceFromBatch;

    @Column(name = "PROFIT_MARGIN")
    private Double profitMargin;

    @Column(name = "TABLE_NAME_EN")
    private String tableNameEn;

    @Column(name = "INTERNAL_CLASS_CODE")
    private String internalClassCode;

    @Column(name = "INTERNAL_CLASS_CODE_EN")
    private String internalClassCodeEn;

    @Column(name = "COMMENT_EN")
    private String commentEn;

    @Column(name = "AUTO_GENERATE_CODE")
    private Integer autoGenerateCode;

    @Column(name = "IS_MERGABLE")
    private Boolean isMergable;

    @Column(name = "HAS_BARCODE")
    private Boolean hasBarcode;

    @Column(name = "ITEM_CLASS_TYPE_ID")
    private Integer itemClassTypeId;

    @Column(name = "IS_DEFAULT")
    private Boolean isDefault;

    @Enumerated(EnumType.STRING)
    @Column(name = "MANAGEMENT_METHOD")
    private ItemEnums.ManagementMethod managementMethod;

    @Column(name = "SERIAL_COUNT")
    private Integer serialCount;

    @Column(name = "HAS_VERSION")
    private Boolean hasVersion;

    @Column(name = "USE_STANDARD_COLORS")
    private Boolean useStandardColors;

    @Enumerated(EnumType.STRING)
    @Column(name = "VALUATION_METHOD")
    private ItemEnums.ValuationMethod valuationMethod;

    @Column(name = "BRANDS_MODELS_OPTIONS")
    private Integer brandsModelsOptions;

    @Column(name = "COMPANY_ID")
    private Integer companyId;

    @Column(name = "QUANTITY_CHANGABLE")
    private Boolean quantityChangable;

    @Column(name = "HAS_CROSS_NET_WEIGHT")
    private Boolean hasCrossNetWeight;

    @Column(name = "ITEM_CLASS_PACKING_UNIT_ID")
    private Integer itemClassPackingUnitId;

    @Enumerated(EnumType.STRING)
    @Column(name = "ITEM_CLASS_ITEMS_TYPE_ID")
    private ItemEnums.ItemType itemClassItemsTypeId;

    @Column(name = "PURCHASING_COST_GL_ACCOUNT_CODE")
    private String purchasingCostGlAccountCode;

    @Column(name = "SALES_COST_GL_ACCOUNT_CODE")
    private String salesCostGlAccountCode;

    @Column(name = "SALES_REVENUE_GL_ACCOUNT_CODE")
    private String salesRevenueGlAccountCode;

    @Column(name = "LOSES_GL_ACCOUNT_CODE")
    private String losesGlAccountCode;

    @Column(name = "SERIAL_IS_REQUIRED")
    private Boolean serialIsRequired;

    @Column(name = "PERIODIC_MAINTENANCE_SERVICE_ITEM_SID")
    private Integer periodicMaintenanceServiceItemSid;

    @Column(name = "PERIODIC_MAINTENANCE_SERVICE_MONTHS")
    private Integer periodicMaintenanceServiceMonths;

    @Column(name = "HAS_ONE_UNIT")
    private Boolean hasOneUnit;

    @Column(name = "ADDITIONS")
    private Boolean additions;


    @PrePersist
    protected void onCreate() {
        creationDate = new Date();
        modificationDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        modificationDate = new Date();
    }

}
