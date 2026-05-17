// 선택한 택시 호출 후보의 상세 응답 값을 전달하는 DTO
package com.sopt.kakaotaxi.domain.ride.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RideTaxiDetailResponse(
	@JsonProperty("taxi_id")
	Long taxiId,
	String taxiType,
	String modelName,
	String modelColor,
	String plateNumber,
	String driverName
) {
}
