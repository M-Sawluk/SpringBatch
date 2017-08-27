package com.michal.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = HeatOfCombustionTypeEntity.TABLE_NAME)
@NamedQueries({
        @NamedQuery(name = HeatOfCombustionTypeEntity.FIND_ALL, query = "select o from HeatOfCombustionTypeEntity o"),
		@NamedQuery(name = HeatOfCombustionTypeEntity.FIND_BY_ID, query = "select o from HeatOfCombustionTypeEntity o where o.id = :id"),
		@NamedQuery(name = HeatOfCombustionTypeEntity.FIND_BY_NAME, query = "select o from HeatOfCombustionTypeEntity o where o.name = (:heatOfCombustionType)") })
public class HeatOfCombustionTypeEntity {

	public static final String TABLE_NAME = "CIEPLO_SPALANIA_TYP";

	public static final String FIND_ALL = "HeatOfCombustionTypeEntity.findAll";
	public static final String FIND_BY_ID = "HeatOfCombustionTypeEntity.findById";
	public static final String FIND_BY_NAME = "HeatOfCombustionTypeEntity.findByName";

	@GeneratedValue
	@Id
	@Basic(optional = false)
	@Column(name = "CIEPLO_SPALANIA_TYP_ID")
	private Integer id;

	@Enumerated(EnumType.STRING)
	private HeatOfCombustionType name;

	public HeatOfCombustionTypeEntity(HeatOfCombustionType name) {
		this.name = name;
	}

	public HeatOfCombustionTypeEntity() {
	}
}