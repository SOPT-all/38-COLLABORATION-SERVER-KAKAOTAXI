package com.sopt.kakaotaxi.domain.place.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sopt.kakaotaxi.domain.place.dto.RecentPlaceResponse;
import com.sopt.kakaotaxi.domain.place.entity.Place;
import com.sopt.kakaotaxi.domain.place.repository.PlaceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceService {

	private final PlaceRepository placeRepository;

	public List<String> getFavoritePlaces(Long userId) {
		List<Place> places = placeRepository.findFavoritePlaces(userId);

		return places.stream()
			.map(Place::getName)
			.toList();
	}

	public List<RecentPlaceResponse> getRecentPlaces(Long userId) {
		List<Place> places = placeRepository.findTop4ByUserIdOrderByLastVisitedAtDesc(userId);

		return places.stream()
			.map(RecentPlaceResponse::from)
			.toList();
	}
}
