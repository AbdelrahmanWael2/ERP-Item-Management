package com.example.demo.Entity;



import jakarta.persistence.*;

import java.io.Serializable;


/**
 * The primary key class for the variant_dimension_values database table.
 * 
 */
@Embeddable
public class VariantDimensionValuePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DIMENSION_ID")
	private int dimensionId;

	@Column(name="VALUE_SER")
	private int valueSer;

	public VariantDimensionValuePK() {
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
		if (!(other instanceof VariantDimensionValuePK)) {
			return false;
		}
		VariantDimensionValuePK castOther = (VariantDimensionValuePK)other;
		return 
			(this.dimensionId == castOther.dimensionId)
			&& (this.valueSer == castOther.valueSer);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.dimensionId;
		hash = hash * prime + this.valueSer;
		
		return hash;
	}
}
