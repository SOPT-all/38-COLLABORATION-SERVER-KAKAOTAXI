// 택시 호출 후보 조회 요청 값을 전달하는 DTO
package com.sopt.kakaotaxi.domain.ride.dto;

public record RideTaxiRequest(
	Long placeId
) {
}
