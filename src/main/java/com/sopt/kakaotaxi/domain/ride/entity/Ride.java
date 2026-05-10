package com.sopt.kakaotaxi.domain.ride.entity;

import com.sopt.kakaotaxi.domain.place.entity.Place;
import com.sopt.kakaotaxi.domain.taxi.entity.Taxi;
import com.sopt.kakaotaxi.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "rides")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ride {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal fare;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "taxi_id", nullable = false)
	private Taxi taxi;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "destination_place_id", nullable = false)
	private Place destination;
}
