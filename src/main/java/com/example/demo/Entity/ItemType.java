package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "item_types")
public class ItemType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid")
    private Integer sid;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "name_en", length = 45)
    private String nameEn;

    @Column(name = "active_flag")
    private Byte activeFlag;

    @Column(name = "created_by")
    private Integer createdBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "modified_by")
    private Integer modifiedBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "modification_date")
    private Date modificationDate;
}
