package com.sopt.kakaotaxi.domain.user.entity;

import com.sopt.kakaotaxi.domain.place.entity.Place;
import com.sopt.kakaotaxi.domain.ride.entity.Ride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false, length = 50)
	private String name;

	@OneToMany(mappedBy = "user")
	private List<Place> places = new ArrayList<>();

	@OneToMany(mappedBy = "user")
	private List<Ride> rides = new ArrayList<>();
}
