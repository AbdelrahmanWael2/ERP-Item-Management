package com.example.demo.Entity;





import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


/**
 * The persistent class for the variant_dimension_values database table.
 * 
 */
@Entity
@Data
@Table(name="variant_dimension_values")
@NamedQuery(name="VariantDimensionValue.findAll", query="SELECT v FROM VariantDimensionValue v")
public class VariantDimensionValue implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private VariantDimensionValuePK id;

	@Column(name="active_flag")
	private byte activeFlag;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATION_DATE")
	private Date creationDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MODIFICATION_DATE")
	private Date modificationDate;

	@Column(name="MODIFIED_BY")
	private String modifiedBy;

	@Column(name="VALUE_CODE")
	private String valueCode;

	@Column(name="VALUE_NAME_AR")
	private String valueNameAr;

	@Column(name="VALUE_NAME_EN")
	private String valueNameEn;
	
	
	
	
	 @ManyToOne( fetch = FetchType.EAGER )
	        @MapsId("dimensionId")
	        @JoinColumn(
	                        name = "DIMENSION_ID" , referencedColumnName = "DIMENSION_ID"  )
	 @JsonBackReference
	// @JsonIgnore
	 private VariantDimension dimention ;


}
