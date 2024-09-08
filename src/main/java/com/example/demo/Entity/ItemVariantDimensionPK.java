package com.example.demo.Entity;



import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;


/**
 * The primary key class for the item_variant_dimensions database table.
 * 
 */
@Embeddable
public class ItemVariantDimensionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DIMENSION_ID")
	private int dimensionId;

	@Column(name="item_sid")
	private int itemSid;

	public ItemVariantDimensionPK() {
	}
	public int getDimensionId() {
		return this.dimensionId;
	}
	public void setDimensionId(int dimensionId) {
		this.dimensionId = dimensionId;
	}
	public int getItemSid() {
		return this.itemSid;
	}
	public void setItemSid(int itemSid) {
		this.itemSid = itemSid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ItemVariantDimensionPK)) {
			return false;
		}
		ItemVariantDimensionPK castOther = (ItemVariantDimensionPK)other;
		return 
			(this.dimensionId == castOther.dimensionId)
			&& (this.itemSid == castOther.itemSid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.dimensionId;
		hash = hash * prime + this.itemSid;
		
		return hash;
	}
}