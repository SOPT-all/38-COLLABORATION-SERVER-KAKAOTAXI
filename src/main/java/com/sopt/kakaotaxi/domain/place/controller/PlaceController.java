package com.sopt.kakaotaxi.domain.place.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "장소", description = "사용자 장소 조회 API")
public class PlaceController {

	private final PlaceService placeService;

	@Operation(summary = "즐겨찾기 장소 조회", description = "사용자의 즐겨찾기 장소 이름 목록을 조회합니다.")
	@GetMapping("/favorite")
	public ResponseEntity<BaseResponse<List<String>>> getFavoritePlaces(
		@Parameter(description = "사용자 ID", example = "1", required = true)
		@RequestHeader ("X-User-Id") Long userId
	) {
		return ResponseEntity.ok(
			BaseResponse.success(placeService.getFavoritePlaces(userId))
		);
	}

	@Operation(summary = "최근 방문 장소 조회", description = "사용자의 최근 방문 장소 목록을 조회합니다.")
	@GetMapping("/recent")
	public ResponseEntity<BaseResponse<List<RecentPlaceResponse>>> getRecentPlaces(
		@Parameter(description = "사용자 ID", example = "1", required = true)
		@RequestHeader("X-User-Id") Long userId
	) {
		return ResponseEntity.ok(
			BaseResponse.success(placeService.getRecentPlaces(userId))
		);
	}
}
