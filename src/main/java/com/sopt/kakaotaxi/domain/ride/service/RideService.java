// 택시 호출 후보 조회 비즈니스 로직을 처리하는 서비스
package com.sopt.kakaotaxi.domain.ride.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sopt.kakaotaxi.domain.ride.dto.RideTaxiResponse;
import com.sopt.kakaotaxi.domain.ride.entity.Ride;
import com.sopt.kakaotaxi.domain.ride.repository.RideRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RideService {

	private final RideRepository rideRepository;

	public List<RideTaxiResponse> getRideTaxis(Long userId, Long placeId) {
		return rideRepository.findAllByUserIdAndDestinationId(userId, placeId)
			.stream()
			.map(this::toResponse)
			.toList();
	}

	private RideTaxiResponse toResponse(Ride ride) {
		return new RideTaxiResponse(
			ride.getTaxi().getId(),
			ride.getTaxi().getType().getDisplayName(),
			ride.getFare().stripTrailingZeros().toPlainString()
		);
	}
}
