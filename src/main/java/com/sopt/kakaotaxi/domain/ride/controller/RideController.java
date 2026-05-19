package com.sopt.kakaotaxi.domain.ride.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sopt.kakaotaxi.domain.ride.dto.RideTaxiDetailResponse;
import com.sopt.kakaotaxi.domain.ride.dto.RideTaxiResponse;
import com.sopt.kakaotaxi.domain.ride.service.RideService;
import com.sopt.kakaotaxi.global.response.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/rides")
@RequiredArgsConstructor
@Tag(name = "택시 호출", description = "택시 호출 후보 조회 API")
public class RideController {

	private final RideService rideService;

	@Operation(summary = "택시 호출 후보 조회", description = "호출 가능한 모든 택시 후보를 조회합니다.")
	@PostMapping
	public ResponseEntity<BaseResponse<List<RideTaxiResponse>>> getRideTaxis() {
		return ResponseEntity.ok(
			BaseResponse.success(
				rideService.getRideTaxis()
			)
		);
	}

	@Operation(summary = "택시 호출 후보 상세 조회", description = "선택한 택시 호출 후보의 상세 정보를 조회합니다.")
	@GetMapping("/{taxi_id}")
	public ResponseEntity<BaseResponse<RideTaxiDetailResponse>> getRideTaxiDetail(
		@Parameter(description = "사용자 ID", example = "1", required = true)
		@RequestHeader("X-User-Id") Long userId,
		@Parameter(name = "taxi_id", description = "택시 ID", example = "1", required = true)
		@PathVariable("taxi_id") Long taxiId
	) {
		return ResponseEntity.ok(
			BaseResponse.success(
				rideService.getRideTaxiDetail(userId, taxiId)
			)
		);
	}
}
