package com.sopt.kakaotaxi.domain.ride.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "택시 호출 후보 상세 응답")
public record RideTaxiDetailResponse(
	@Schema(description = "택시 ID", example = "1")
	@JsonProperty("taxi_id")
	Long taxiId,
	@Schema(description = "택시 종류", example = "일반택시")
	String taxiType,
	@Schema(description = "차량 모델명", example = "쏘나타")
	String modelName,
	@Schema(description = "차량 색상", example = "검정")
	String modelColor,
	@Schema(description = "차량 번호", example = "12가1234")
	String plateNumber,
	@Schema(description = "기사 이름", example = "김기사")
	String driverName
) {
}
