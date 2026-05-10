package com.sopt.kakaotaxi.domain.taxi.entity;

import com.sopt.kakaotaxi.domain.ride.entity.Ride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
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

	@OneToMany(mappedBy = "taxi")
	private List<Ride> rides = new ArrayList<>();
}
