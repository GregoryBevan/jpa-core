package com.elgregos.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.elgregos.jpa.BaseEntity;

@Entity
@Table(name = "animal")
@SequenceGenerator(name = "seq_gen", sequenceName = "animal_seq", allocationSize = 1)
public class Animal extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "species")
	private String species;

}
