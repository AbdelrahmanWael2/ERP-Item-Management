package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name="ph_items")
@NamedQuery(name="PhItem.findAll", query="SELECT p FROM PhItem p")
public class PhItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ITEM_SID")
    private Integer itemSid;

    @Column(name="ACTIVE_FLAG")
    private Byte activeFlag;



    @Column(name="CAN_BE_ADDITION")
    private byte canBeAddition;

    @Column(name="COMMENT")
    private String comment;

    @Column(name="COMMENT_EN")
    private String commentEn;

    @Column(name="CUSTOM_GROUP_ID")
    private Integer customGroupId;

    @Column(name="ITEM_CLASS_ID")
    private Integer itemClassId;

    @Column(name="ITEM_CODE")
    private String itemCode;

    @Column(name="ITEM_GROUP_ID")
    private Integer itemGroupId;

    @Column(name="NAME_AR")
    private String nameAr;

    @Column(name="NAME_EN")
    private String nameEn;

    @Column(name="TYPE")
    private Integer type;

    @Column(name="HAS_VARIANTS")
    private Byte hasVariants;

    @Column(name="ITEM_TYPE")
    private Integer itemType;

    @Column(name="HAS_WARRANTY")
    private Byte hasWarranty;

    @Column(name="SHELF_NUMBER")
    private String shelfNumber;

//    @Column(name="PACKING_UNIT_ID")
//    private Integer packingUnitId;
//
//    @Column(name="SELLABLE")
//    private Boolean sellable;
//
//    @Column(name="PURCHASABLE")
//    private Boolean purchasable;
//
//    @Column(name="STOCKABLE")
//    private Boolean stockable;


//
//    @Column(name="HAS_VERSIONS")
//    private Byte hasVersions;

//    // Stock-related fields
//    @Column(name="AVALIABLE_QUANTITY")
//    private double avaliableQuantity;
//
//    @Column(name="COMMITED_QUANTITY")
//    private double commitedQuantity;
//
//    @Column(name="MIN_STOCK_LEVEL")
//    private double minStockLevel;
//
//    @Column(name="ORDERD_QUANTITY")
//    private double orderdQuantity;
//
//    @Column(name="QOH")
//    private double qoh;
//
//    @Column(name="REORDER_POINT")
//    private double reorderPoint;
//
//    @Column(name="WEIGHTED_AVERAGE_COST")
//    private double weightedAverageCost;



//    @Column(name="SALES_DISCOUNT_GROUP_ID")
//    private Integer salesDiscountGroupId;

    // Extra attributes (commented out)
    /*
    @Column(name="ALLOW_NEGATIVE_BALANCE")
    private Byte allowNegativeBalance;

    @Column(name="APPEAR_IN_ALL_BUSINESS_UNITS")
    private Byte appearInAllBusinessUnits;

    @Column(name="APPROXIMATE_CONVERSION_FACTOR")
    private Double approximateConversionFactor;

    @Column(name="AVG_COST")
    private Double avgCost;

    @Column(name="AVG_QOH")
    private Double avgQoh;

    @Column(name="AVG_SELLING")
    private Double avgSelling;

    @Column(name="BOM_ID")
    private Integer bomId;

    @Column(name="BOM_TYPE")
    private Integer bomType;

    @Column(name="BRAND_ID")
    private Integer brandId;

    @Column(name="BUSINESS_UNIT_ID")
    private Integer businessUnitId;

    @Column(name="COMPANY_ID")
    private Integer companyId;

    @Column(name="COMPOSITION_FLAG")
    private Byte compositionFlag;

    @Column(name="CREATED_BY")
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATION_DATE")
    private Date creationDate;

    @Column(name="DEFAULT_VENDOR_ID")
    private Integer defaultVendorId;

    @Column(name="DIFFERENTIATE_GROUPS")
    private Byte differentiateGroups;

//    @Column(name="BAR_CODE")
//    private String barCode;
//
//    @Column(name="BAR_CODE_EN")
//    private String barCodeEn;

    @Column(name="EXPIRY_DAYS")
    private Integer expiryDays;

    @Column(name="EXPIRY_MONTHS")
    private Integer expiryMonths;

    @Column(name="EXPIRY_YEARS")
    private Integer expiryYears;

    @Column(name="GARD_QUANTITY")
    private Double gardQuantity;

    @Column(name="GENERIC_ID")
    private Integer genericId;

    @Column(name="HAS_PERIODIC_MAINTENANCE")
    private Byte hasPeriodicMaintenance;



    @Column(name="INTERNAL_CLASS_CODE")
    private String internalClassCode;

    @Column(name="ITEM_COMPANY")
    private Integer itemCompany;

    @Column(name="ITEM_MODEL")
    private String itemModel;



    @Column(name="ITEM_VERSION_SID")
    private Integer itemVersionSid;

    @Column(name="LAST_PURCHASE_PRICE")
    private Double lastPurchasePrice;

    @Column(name="LAST_VENDOR_ID")
    private Integer lastVendorId;

    @Column(name="LIMIT_VENDORS")
    private Byte limitVendors;

    @Column(name="LOSES_GL_ACCOUNT_CODE")
    private String losesGlAccountCode;

    @Column(name="MIN_QTY")
    private Double minQty;

    @Column(name="MODEL_ID")
    private Integer modelId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="MODIFICATION_DATE")
    private Date modificationDate;

    @Column(name="MODIFIED_BY")
    private String modifiedBy;

    @Column(name="NEW_ITEM_CODE")
    private String newItemCode;

    @Column(name="ORDER_LEVEL")
    private Integer orderLevel;

    @Column(name="PARENT_ITEM_SID")
    private Integer parentItemSid;

    @Column(name="PARTNER_ID")
    private Integer partnerId;

    @Column(name="PARTNER_TYPE")
    private String partnerType;

    @Column(name="PLANT_ID")
    private Integer plantId;

    @Column(name="PLANT_TYPE_ID")
    private Integer plantTypeId;

    @Column(name="PRODUCTION_OPERATION_SID")
    private Integer productionOperationSid;

    @Column(name="PURCHASING_COST_GL_ACCOUNT_CODE")
    private String purchasingCostGlAccountCode;

    @Column(name="PURCHASING_DISCOUNT_GROUP_ID")
    private Integer purchasingDiscountGroupId;

    @Column(name="PURCHASING_PLAN_QUANTITY")
    private Integer purchasingPlanQuantity;

    @Column(name="QUANTITY_BY_TWO_UNITS")
    private Byte quantityByTwoUnits;

    @Column(name="REPORTING_RANK")
    private Integer reportingRank;

    @Column(name="SALES_COST_GL_ACCOUNT_CODE")
    private String salesCostGlAccountCode;



    @Column(name="SALES_REVENUE_GL_ACCOUNT_CODE")
    private String salesRevenueGlAccountCode;

    @Column(name="SERIAL_SUFFIX")
    private String serialSuffix;


    @Column(name="SCIENTIFIC_NAME")
    private String scientificName;

    @Column(name="SCIENTIFIC_NAME_EN")
    private String scientificNameEn;

    @Column(name="TAKE_CRITERIA")
    private Integer takeCriteria;

    @Column(name="TAX_AMOUNT")
    private Double taxAmount;

    @Column(name="TAX_PERCENTAGE")
    private Double taxPercentage;

    @Column(name="UDF1")
    private String udf1;

    @Column(name="UDF2")
    private String udf2;

    @Column(name="UDF3")
    private String udf3;

    @Column(name="UDF4")
    private String udf4;

    @Column(name="UDF5")
    private String udf5;

    @Column(name="UDF6")
    private String udf6;

    @Column(name="UDF7")
    private String udf7;

    @Column(name="WARRANTY_MONTHS")
    private Integer warrantyMonths;

    @Column(name="WARRANTY_MONTHS_AFTER_END_WARRANTY")
    private Integer warrantyMonthsAfterEndWarranty;

    @Column(name="WARRANTY_YEARS")
    private Double warrantyYears;
    */
}