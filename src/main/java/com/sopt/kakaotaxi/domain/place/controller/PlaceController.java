package com.sopt.kakaotaxi.domain.place.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sopt.kakaotaxi.domain.place.dto.PlaceResponseDto;
import com.sopt.kakaotaxi.domain.place.service.PlaceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/places")
@RequiredArgsConstructor
public class PlaceController {

	private final PlaceService placeService;

	@GetMapping("/favorite")
	public ResponseEntity<PlaceResponseDto> getFavoritePlaces(@RequestHeader ("X-User-Id") Long userId) {
		return ResponseEntity.ok(placeService.getFavoritePlaces(userId));
	}
}
