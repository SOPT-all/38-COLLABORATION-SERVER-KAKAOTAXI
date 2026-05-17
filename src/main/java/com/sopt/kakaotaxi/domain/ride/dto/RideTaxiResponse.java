// 택시 호출 후보 조회 응답 값을 전달하는 DTO
package com.sopt.kakaotaxi.domain.ride.dto;

public record RideTaxiResponse(
	Long taxiId,
	String taxiType,
	String estimatedFare
) {
}
