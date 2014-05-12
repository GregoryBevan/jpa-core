package com.elgregos.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.elgregos.jpa.BaseEntity;

@Entity
@Table(name = "person")
@SequenceGenerator(name = "seq_gen", sequenceName = "person_seq", allocationSize = 1)
public class Person extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "firstname")
	private String firstname;

}
