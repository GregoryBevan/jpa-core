package com.elgregos.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Base for all entities
 *
 * @author Grégory
 */
@MappedSuperclass
@Getter
@EqualsAndHashCode(doNotUseGetters = true, of = "id")
@ToString(doNotUseGetters = true, of = "id")
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Id for entity
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
	@Column(name = "id", nullable = false)
	private Long id;

	/**
	 * Version for optimistic lock
	 */
	@Column(name = "version")
	@Version
	private Long version;
}
