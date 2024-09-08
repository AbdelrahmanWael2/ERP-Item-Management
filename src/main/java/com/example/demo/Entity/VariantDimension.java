package com.example.demo.Entity;



import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the variant_dimensions database table.
 * 
 */
@Entity
@Data
@Table(name="variant_dimensions")
@NamedQuery(name="VariantDimension.findAll", query="SELECT v FROM VariantDimension v")
public class VariantDimension implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DIMENSION_ID")
	private int dimensionId;

	@Column(name="ACTIVE_FLAG")
	private byte activeFlag;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATION_DATE")
	private Date creationDate;

	@Column(name="DIMENSION_NAME_AR")
	private String dimensionNameAr;

	@Column(name="DIMENSION_NAME_EN")
	private String dimensionNameEn;
	
	@Column(name="STANDARD_DIMENSION")
	private byte standardDimension;
	  
   @Column(name="display_type")
	private Integer displayType ;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MODIFICATION_DATE")
	private Date modificationDate;

	@Column(name="MODIFIED_BY")
	private String modifiedBy;

	
	
	
	@OneToMany( cascade = {CascadeType.ALL} ,mappedBy="dimention"  ,fetch = FetchType.LAZY)// 
	@JsonManagedReference
	
	List<VariantDimensionValue> dimensionValues ;
	
	

	
}
