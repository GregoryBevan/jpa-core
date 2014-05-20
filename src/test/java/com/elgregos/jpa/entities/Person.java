package com.elgregos.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.elgregos.jpa.BaseEntity;

@Entity
@Table(name = "person")
@SequenceGenerator(name = "seq_gen", sequenceName = "person_seq", allocationSize = 1)
@NamedQueries({ @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
	@NamedQuery(name = "Person.findByLastname", query = "SELECT p FROM Person p WHERE p.lastname = :lastname") })
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
public class Person extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "firstname")
	private String firstname;

}
