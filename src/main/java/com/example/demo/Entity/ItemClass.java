package com.example.demo.Entity;

import com.example.demo.Enums.ItemEnums;
import jakarta.persistence.*;
import java.util.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@Table(name = "ph_item_classes")
public class ItemClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ITEM_CLASS_ID")
    private Integer itemClassId;

    @Column(name = "NAME")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_OF_CODE")
    private ItemEnums.TypeOfCoding typeOfCode;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "ACTIVE_FLAG")
    private Boolean activeFlag;

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

    @Column(name = "HAS_BARCODE")
    private Boolean hasBarcode;

    @Enumerated(EnumType.STRING)
    @Column(name = "MANAGEMENT_METHOD")
    private ItemEnums.ManagementMethod managementMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "VALUATION_METHOD")
    private ItemEnums.ValuationMethod valuationMethod;

    @Column(name = "BRANDS_MODELS_OPTIONS")
    private ItemEnums.BrandsModelsOptions brandsModelsOptions;

    @Column(name = "QUANTITY_CHANGABLE")
    private Boolean quantityChangable;

    @Column(name = "PERIODIC_MAINTENANCE_SERVICE_ITEM_SID")
    private Integer periodicMaintenanceServiceItemSid;

    @Column(name = "PERIODIC_MAINTENANCE_SERVICE_MONTHS")
    private Integer periodicMaintenanceServiceMonths;

    @Column(name = "HAS_ONE_UNIT")
    private Boolean hasOneUnit;

    @Column(name = "IS_MERGABLE")
    private Boolean isMergable;

    @Column(name = "ITEM_TYPE")
    private ItemEnums.ItemType itemType;

    @Column(name = "CUSTOMER_SERVICE")
    private Boolean customerService;


}
