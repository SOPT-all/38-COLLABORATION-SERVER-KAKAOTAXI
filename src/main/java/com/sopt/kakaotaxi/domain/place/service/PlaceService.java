package com.sopt.kakaotaxi.domain.place.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sopt.kakaotaxi.domain.place.dto.PlaceResponseDto;
import com.sopt.kakaotaxi.domain.place.entity.Place;
import com.sopt.kakaotaxi.domain.place.repository.PlaceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceService {

	private final PlaceRepository placeRepository;

	public PlaceResponseDto getFavoritePlaces(Long userId) {
		List<Place> places = placeRepository.findFavoritePlaces(userId);
		return PlaceResponseDto.from(places);
	}
}
