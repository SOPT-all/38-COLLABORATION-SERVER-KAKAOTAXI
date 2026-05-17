package com.sopt.kakaotaxi.domain.place.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sopt.kakaotaxi.domain.place.dto.RecentPlaceResponse;
import com.sopt.kakaotaxi.domain.place.service.PlaceService;
import com.sopt.kakaotaxi.global.response.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/places")
@RequiredArgsConstructor
public class PlaceController {

	private final PlaceService placeService;

	@GetMapping("/favorite")
	public ResponseEntity<BaseResponse<List<String>>> getFavoritePlaces(
		@RequestHeader ("X-User-Id") Long userId
	) {
		return ResponseEntity.ok(
			BaseResponse.success(placeService.getFavoritePlaces(userId))
		);
	}

	@GetMapping("/recent")
	public ResponseEntity<BaseResponse<List<RecentPlaceResponse>>> getRecentPlaces(
		@RequestHeader("X-User-Id") Long userId
	) {
		return ResponseEntity.ok(
			BaseResponse.success(placeService.getRecentPlaces(userId))
		);
	}
}
