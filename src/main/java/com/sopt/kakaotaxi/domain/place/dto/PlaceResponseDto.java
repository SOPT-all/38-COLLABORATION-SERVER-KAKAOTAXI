package com.sopt.kakaotaxi.domain.place.dto;

import java.util.List;

import com.sopt.kakaotaxi.domain.place.entity.Place;

public record PlaceResponseDto(
	List<String> data
) {
	public static PlaceResponseDto from(List<Place> places) {
		return new PlaceResponseDto(
			places.stream()
				.map(Place::getName)
				.toList()
		);
	}
}
