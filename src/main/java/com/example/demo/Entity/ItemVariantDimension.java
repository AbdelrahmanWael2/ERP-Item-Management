package com.example.demo.Entity;



import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


/**
 * The persistent class for the item_variant_dimensions database table.
 * 
 */
@Entity
@Data
@Table(name="item_variant_dimensions")
@NamedQuery(name="ItemVariantDimension.findAll", query="SELECT i FROM ItemVariantDimension i")
public class ItemVariantDimension implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemVariantDimensionPK id;

	@Column(name="active_flag")
	private byte activeFlag;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creation_date")
	private Date creationDate;

	@Column(name="DIMENSION_SER")
	private int dimensionSer;

	@Column(name="ITEM_CLASS_ID")
	private int itemClassId;

	@Column(name="ITEM_CODE")
	private String itemCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modification_date")
	private Date modificationDate;

	@Column(name="modified_by")
	private String modifiedBy;

	@Column(name="select_default_value")
	private byte selectDefaultValue;

	@Column(name="show_full_price")
	private byte showFullPrice;

	public ItemVariantDimension() {
	}
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	 @MapsId("itemSid")
	@JoinColumn(name ="item_sid"  , referencedColumnName = "item_sid"  ) 
	@JsonBackReference
	PhItem item ;
	
	
	@OneToMany( cascade = {CascadeType.ALL} ,mappedBy="itemVariantDimension"  ,fetch = FetchType.LAZY)
	@JsonManagedReference
	java.util.List<ItemVariantDimensionValue> itemVariantDimensionValues ;
	
    
}
