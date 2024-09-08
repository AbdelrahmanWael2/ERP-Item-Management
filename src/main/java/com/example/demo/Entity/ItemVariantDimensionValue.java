package com.example.demo.Entity;



import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


/**
 * The persistent class for the item_variant_dimension_values database table.
 * 
 */
@Entity
@Data
@Table(name="item_variant_dimension_values")
@NamedQuery(name="ItemVariantDimensionValue.findAll", query="SELECT i FROM ItemVariantDimensionValue i")
public class ItemVariantDimensionValue implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemVariantDimensionValuePK id;

	@Column(name="active_flag")
	private byte activeFlag;

	@Column(name="amount_added")
	private double amountAdded;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creation_date")
	private Date creationDate;

	@Column(name="default_value")
	private byte defaultValue;

	@Column(name="ITEM_CLASS_ID")
	private int itemClassId;

	@Column(name="ITEM_CODE")
	private String itemCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modification_date")
	private Date modificationDate;

	@Column(name="modified_by")
	private String modifiedBy;

	@Column(name="VALUE_CODE")
	private String valueCode;

	@Column(name="VALUE_NAME_AR")
	private String valueNameAr;

	@Column(name="VALUE_NAME_EN")
	private String valueNameEn;
	
	
	
	 @ManyToOne( fetch = FetchType.EAGER )
	 @JoinColumns({
	        @JoinColumn(
	            name = "item_sid"  , referencedColumnName ="item_sid" , updatable = false , insertable = false ),
	        @JoinColumn(
	            name = "dimension_sid" , referencedColumnName = "dimension_id" , updatable = false , insertable = false
	            )
	        
	    })
	 @JsonBackReference
	ItemVariantDimension    itemVariantDimension ;
	 
	 
	 @ManyToOne(fetch = FetchType.EAGER)
	 @MapsId("itemSid")
	@JoinColumn(name ="item_sid"  , referencedColumnName = "item_sid"  ) 
	@JsonIgnore
	PhItem item ;


}
