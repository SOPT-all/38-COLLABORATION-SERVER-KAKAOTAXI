package com.sopt.kakaotaxi.domain.ride.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "택시 호출 후보 조회 응답")
public record RideTaxiResponse(
	@Schema(description = "택시 ID", example = "1")
	Long taxiId,
	@Schema(description = "택시 종류", example = "일반택시")
	String taxiType,
	@Schema(description = "예상 요금", example = "12000")
	String estimatedFare
) {
}
