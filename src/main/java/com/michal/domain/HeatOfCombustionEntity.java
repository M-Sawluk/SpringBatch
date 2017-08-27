package com.michal.domain;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = HeatOfCombustionEntity.TABLE_NAME)
@NamedQueries({
        @NamedQuery(name = HeatOfCombustionEntity.FIND_ALL, query = "select o from HeatOfCombustionEntity o"),
		@NamedQuery(name = HeatOfCombustionEntity.FIND_BY_ID, query = "select o from HeatOfCombustionEntity o where o.id = :id"),
		@NamedQuery(name = HeatOfCombustionEntity.FIND_BY_GAS_POINT_IDS, query = "select o from HeatOfCombustionEntity o where o.gasPoint.id in (:gasPointIds)") })
public class HeatOfCombustionEntity {
	private static final long serialVersionUID = -1715719203634682931L;

	public static final String SEQ_STORE_NAME = "CIEPLO_SPALANIA_STORE_SEQ";

	public static final String TABLE_NAME = "CIEPLO_SPALANIA";

	public static final String FIND_ALL = "HeatOfCombustionEntity.findAll";
	public static final String FIND_BY_ID = "HeatOfCombustionEntity.findById";
	public static final String FIND_BY_GAS_POINT_IDS = "HeatOfCombustionEntity.findByGasPointIds";
	public static final String FIND_BY_EXAMPLE= "HeatOfCombustionEntity.findByExample";
	
	
	@GeneratedValue
	@Id
	@Basic(optional = false)
	@Column(name = "CIEPLO_SPALANIA_ID")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "TYP_CIEPLA_SPALANIA_ID", nullable = false)
	private HeatOfCombustionTypeEntity heatOfCombustionType;

	@Column(name = "DATA_OD", nullable = false)
	private Date dateFrom;

	@Column(name = "DATA_DO", nullable = false)
	private Date dateTo;

	@ManyToOne
	@JoinColumn(name = "PUNKT_GAZOWY_IDENT")
	protected GasPointEntity gasPoint;

	@Column(name = "WARTOSC_WSPOLCZYNNIKA")
	@Digits(integer = 22, fraction = 7)
	private BigDecimal value;

	@ManyToOne
	@JoinColumn(name = "JEDNOSTKA_ID")
	private MeasureUnitVersionEntity measureUnit;

	@OneToMany(mappedBy = "heatOfCombustion", orphanRemoval = true, fetch=FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private List<HeatOfCombustionValuesEntity> heatOfCombustionValues = new ArrayList<>();


}