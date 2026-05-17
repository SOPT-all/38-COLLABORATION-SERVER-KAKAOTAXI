package com.sopt.kakaotaxi.domain.place.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sopt.kakaotaxi.domain.place.entity.Place;

public record RecentPlaceResponse(
	Long id,
	String name,
	@JsonFormat(pattern = "yyyy.MM.dd'T'HHmmss")
	LocalDateTime last_visited_at,
	String address
) {
	public static RecentPlaceResponse from(Place place) {
		return new RecentPlaceResponse(
			place.getId(),
			place.getName(),
			place.getLastVisitedAt(),
			place.getAddress()
		);
	}
}
