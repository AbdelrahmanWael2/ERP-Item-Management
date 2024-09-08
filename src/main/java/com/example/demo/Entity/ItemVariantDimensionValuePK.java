package com.example.demo.Entity;



import jakarta.persistence.*;

import java.io.Serializable;


/**
 * The primary key class for the item_variant_dimension_values database table.
 * 
 */
@Embeddable
public class ItemVariantDimensionValuePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="item_sid")
	private int itemSid;

	@Column(name="DIMENSION_ID")
	private int dimensionId;

	@Column(name="VALUE_SER")
	private int valueSer;

	public ItemVariantDimensionValuePK() {
	}
	public int getItemSid() {
		return this.itemSid;
	}
	public void setItemSid(int itemSid) {
		this.itemSid = itemSid;
	}
	public int getDimensionId() {
		return this.dimensionId;
	}
	public void setDimensionId(int dimensionId) {
		this.dimensionId = dimensionId;
	}
	public int getValueSer() {
		return this.valueSer;
	}
	public void setValueSer(int valueSer) {
		this.valueSer = valueSer;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ItemVariantDimensionValuePK)) {
			return false;
		}
		ItemVariantDimensionValuePK castOther = (ItemVariantDimensionValuePK)other;
		return 
			(this.itemSid == castOther.itemSid)
			&& (this.dimensionId == castOther.dimensionId)
			&& (this.valueSer == castOther.valueSer);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.itemSid;
		hash = hash * prime + this.dimensionId;
		hash = hash * prime + this.valueSer;
		
		return hash;
	}
}
