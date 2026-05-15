package com.sopt.kakaotaxi.domain.place.entity;

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
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "places")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(nullable = false, length = 255)
	private String address;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private PlaceType type;

	@Column(name = "visit_count", nullable = false)
	private Integer visitCount;

	@Column(name = "last_visited_at", nullable = false)
	private LocalDateTime lastVisitedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Builder(access = AccessLevel.PRIVATE)
	private Place(String name, String address, PlaceType type,
		Integer visitCount, LocalDateTime lastVisitedAt, User user) {
		this.name = name;
		this.address = address;
		this.type = type;
		this.visitCount = visitCount;
		this.lastVisitedAt = lastVisitedAt != null ? lastVisitedAt : LocalDateTime.now();
		this.user = user;
	}

	public static Place createEtc(String name, String address, int visitCount, User user) {
		return Place.builder()
			.name(name)
			.address(address)
			.type(PlaceType.ETC)
			.visitCount(visitCount)
			.user(user)
			.build();
	}

	public static Place createEtc(String name, String address, int visitCount,
		LocalDateTime lastVisitedAt, User user) {
		return Place.builder()
			.name(name)
			.address(address)
			.type(PlaceType.ETC)
			.visitCount(visitCount)
			.lastVisitedAt(lastVisitedAt)
			.user(user)
			.build();
	}

	public static Place createHome(String name, String address, User user) {
		return Place.builder()
			.name(name)
			.address(address)
			.type(PlaceType.HOME)
			.visitCount(0)
			.user(user)
			.build();
	}
}
