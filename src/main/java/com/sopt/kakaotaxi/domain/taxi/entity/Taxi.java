package com.sopt.kakaotaxi.domain.taxi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "taxis")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Taxi {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false, length = 30)
	private String plateNumber;

	@Column(nullable = false, length = 50)
	private String driverName;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private TaxiType type;

	private Taxi(String plateNumber, String driverName, TaxiType type) {
		this.plateNumber = plateNumber;
		this.driverName = driverName;
		this.type = type;
	}

	public static Taxi create(String plateNumber, String driverName, TaxiType type) {
		return new Taxi(plateNumber, driverName, type);
	}
}
