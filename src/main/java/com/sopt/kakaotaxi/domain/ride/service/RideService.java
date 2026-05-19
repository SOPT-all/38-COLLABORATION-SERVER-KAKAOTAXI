// 택시 호출 후보 조회 비즈니스 로직을 처리하는 서비스
package com.sopt.kakaotaxi.domain.ride.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sopt.kakaotaxi.domain.ride.dto.RideTaxiDetailResponse;
import com.sopt.kakaotaxi.domain.ride.dto.RideTaxiResponse;
import com.sopt.kakaotaxi.domain.ride.entity.Ride;
import com.sopt.kakaotaxi.domain.ride.repository.RideRepository;
import com.sopt.kakaotaxi.domain.taxi.entity.Taxi;
import com.sopt.kakaotaxi.global.exception.BusinessException;
import com.sopt.kakaotaxi.global.exception.CommonErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RideService {

	private final RideRepository rideRepository;

	public List<RideTaxiResponse> getRideTaxis() {
		return rideRepository.findAll()
			.stream()
			.map(this::toResponse)
			.toList();
	}

	public RideTaxiDetailResponse getRideTaxiDetail(Long userId, Long taxiId) {
		Ride ride = rideRepository.findFirstByUserIdAndTaxiId(userId, taxiId)
			.orElseThrow(() -> new BusinessException(CommonErrorCode.RESOURCE_NOT_FOUND));

		return toDetailResponse(ride.getTaxi());
	}

	private RideTaxiResponse toResponse(Ride ride) {
		return new RideTaxiResponse(
			ride.getTaxi().getId(),
			ride.getTaxi().getType().getDisplayName(),
			ride.getFare()
		);
	}

	private RideTaxiDetailResponse toDetailResponse(Taxi taxi) {
		return new RideTaxiDetailResponse(
			taxi.getId(),
			taxi.getType().getDisplayName(),
			taxi.getModelName(),
			taxi.getModelColor(),
			taxi.getPlateNumber(),
			taxi.getDriverName()
		);
	}
}
