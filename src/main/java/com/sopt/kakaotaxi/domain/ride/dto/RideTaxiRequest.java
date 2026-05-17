package com.sopt.kakaotaxi.domain.ride.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "택시 호출 후보 조회 요청")
public record RideTaxiRequest(
	@Schema(description = "도착 장소 ID", example = "10")
	Long placeId
) {
}
