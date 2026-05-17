// 택시 호출 후보 조회 API 요청을 처리하는 컨트롤러
package com.sopt.kakaotaxi.domain.ride.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sopt.kakaotaxi.domain.ride.dto.RideTaxiRequest;
import com.sopt.kakaotaxi.domain.ride.dto.RideTaxiResponse;
import com.sopt.kakaotaxi.domain.ride.service.RideService;
import com.sopt.kakaotaxi.global.response.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/rides")
@RequiredArgsConstructor
public class RideController {

	private final RideService rideService;

	@PostMapping
	public ResponseEntity<BaseResponse<List<RideTaxiResponse>>> getRideTaxis(
		@RequestHeader("X-User-Id") Long userId,
		@RequestBody RideTaxiRequest request
	) {
		return ResponseEntity.ok(
			BaseResponse.success(
				rideService.getRideTaxis(userId, request.placeId())
			)
		);
	}
}
